package main;

import org.lwjgl.opengl.*;
import org.lwjgl.system.jawt.JAWT;
import org.lwjgl.system.jawt.*;

import java.awt.*;
import java.awt.event.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWNativeWin32.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.jawt.JAWTFunctions.*;

/**
 * A Canvas component that uses OpenGL for rendering.
 *
 * <p>This implementation supports Windows only.</p>
 */
@SuppressWarnings("serial")
public class LWJGLCanvas extends Canvas {

    private final JAWT awt;

    private JAWTDrawingSurface ds;

    private long    glfwWindow;
    private boolean resized;

    private GLCapabilities caps;

    public LWJGLCanvas() {
        awt = JAWT.calloc();
        awt.version(JAWT_VERSION_1_4);
        if (!JAWT_GetAWT(awt)) {
            throw new IllegalStateException("GetAWT failed");
        }

        this.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                resized = true;
            }
        });
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (ds == null) {
            // Get the drawing surface
            ds = JAWT_GetDrawingSurface(this, awt.GetDrawingSurface());
            if (ds == null) {
                throw new IllegalStateException("awtOld->GetDrawingSurface() failed");
            }
        }

        // Lock the drawing surface
        int lock = JAWT_DrawingSurface_Lock(ds, ds.Lock());
        if ((lock & JAWT_LOCK_ERROR) != 0) {
            throw new IllegalStateException("ds->Lock() failed");
        }

        try {
            // Get the drawing surface info
            JAWTDrawingSurfaceInfo dsi = JAWT_DrawingSurface_GetDrawingSurfaceInfo(ds, ds.GetDrawingSurfaceInfo());
            if (dsi == null) {
                throw new IllegalStateException("ds->GetDrawingSurfaceInfo() failed");
            }

            try {
                // Get the platform-specific drawing info
                JAWTWin32DrawingSurfaceInfo dsi_win = JAWTWin32DrawingSurfaceInfo.create(dsi.platformInfo());

                long hdc = dsi_win.hdc();
                if (hdc != NULL) {
                    if (glfwWindow == NULL) {
                        // glfwWindowHint can be used here to configure the GL context
                        glfwWindow = glfwAttachWin32Window(dsi_win.hwnd(), NULL);
                        if (glfwWindow == NULL) {
                            throw new IllegalStateException("Failed to attach win32 window.");
                        }

                        glfwMakeContextCurrent(glfwWindow);
                        caps = GL.createCapabilities();

                        glClearColor(1f, 0.5f, 0f, 1f);

                        resized = true;
                    } else {
                        glfwMakeContextCurrent(glfwWindow);
                        GL.setCapabilities(caps);
                    }

                    if (resized) {
                        glfwSetWindowSize(glfwWindow, getWidth(), getHeight());
                        glViewport(0, 0, getWidth(), getHeight());

                        float f = getHeight() / (float)getWidth();

                        glMatrixMode(GL_PROJECTION);
                        glLoadIdentity();
                        glFrustum(-1.0f, 1.0f, -f, f, 5.0f, 100.0f);
                        glMatrixMode(GL_MODELVIEW);

                        resized = false;
                    }

                    glClear(GL_COLOR_BUFFER_BIT);

                    glfwSwapBuffers(glfwWindow);

                    glfwMakeContextCurrent(NULL);
                    GL.setCapabilities(null);
                }
            } finally {
                // Free the drawing surface info
                JAWT_DrawingSurface_FreeDrawingSurfaceInfo(dsi, ds.FreeDrawingSurfaceInfo());
            }
        } finally {
            // Unlock the drawing surface
            JAWT_DrawingSurface_Unlock(ds, ds.Unlock());
        }

        repaint();
    }

    public void destroy() {
        // Free the drawing surface
        JAWT_FreeDrawingSurface(ds, awt.FreeDrawingSurface());

        awt.free();

        if (glfwWindow != NULL) {
            glfwDestroyWindow(glfwWindow);
        }
    }

}
