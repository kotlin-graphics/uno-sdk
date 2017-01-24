package buffer;

import com.jogamp.opengl.util.GLBuffers;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by GBarbieri on 24.01.2017.
 */
public class Main {

    public static void main(String[] args) {

        ByteBuffer bb = GLBuffers.newDirectByteBuffer(100);

        try {
            MMapDirectory.CLEANER.freeBuffer("ciao", bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bb.get(0));
    }
}
