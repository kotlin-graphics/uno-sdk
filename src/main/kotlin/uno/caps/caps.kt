package uno.caps

import com.jogamp.opengl.GL
import com.jogamp.opengl.GL2
import com.jogamp.opengl.GL2.GL_COMPRESSED_LUMINANCE_LATC1_EXT
import com.jogamp.opengl.GL2.GL_COMPRESSED_SIGNED_LUMINANCE_LATC1_EXT
import com.jogamp.opengl.GL2.GL_COMPRESSED_LUMINANCE_ALPHA_LATC2_EXT
import com.jogamp.opengl.GL2.GL_COMPRESSED_SIGNED_LUMINANCE_ALPHA_LATC2_EXT
import com.jogamp.opengl.GL2.GL_MAX_DEEP_3D_TEXTURE_WIDTH_HEIGHT_NV
import com.jogamp.opengl.GL2.GL_MAX_DEEP_3D_TEXTURE_DEPTH_NV
import com.jogamp.opengl.GL2ES1.GL_POINT_SIZE_MAX
import com.jogamp.opengl.GL2ES1.GL_POINT_SIZE_MIN
import com.jogamp.opengl.GL3
import com.jogamp.opengl.GL4.*
import com.jogamp.opengl.GLES1.GL_ETC1_RGB8_OES
import glm.vec._2.Vec2
import uno.buffer.destroy
import uno.buffer.intBufferBig
import uno.gl.*

/**
 * Created by GBarbieri on 10.03.2017.
 */

class Caps(
        gl: GL,
        profile: Profile) {

    @JvmField val version = Version(gl, profile)
    @JvmField val extensions = Extensions(gl)
    @JvmField val debug = Debug(gl)
    @JvmField val limits = Limits(gl)

    inner class Version(gl: GL, val profile: Profile) {

        @JvmField val MINOR_VERSION = gl.getInteger(GL_MINOR_VERSION)
        @JvmField val MAJOR_VERSION = gl.getInteger(GL_MAJOR_VERSION)
        @JvmField val CONTEXT_FLAGS =
                if (check(4, 3) || gl.isExtensionAvailable("GL_KHR_debug"))
                    gl.getInteger(GL_CONTEXT_FLAGS)
                else 0
        @JvmField val NUM_EXTENSIONS = gl.getInteger(GL_NUM_EXTENSIONS)
        @JvmField val RENDERER = gl.getString(GL_RENDERER)
        @JvmField val VENDOR = gl.getString(GL_VENDOR)
        @JvmField val VERSION = gl.getString(GL_VERSION)
        @JvmField val SHADING_LANGUAGE_VERSION = gl.getString(GL_SHADING_LANGUAGE_VERSION)
        @JvmField val NUM_SHADING_LANGUAGE_VERSIONS =
                if (check(4, 3))
                    gl.getInteger(GL_NUM_SHADING_LANGUAGE_VERSIONS)
                else 0


        private val glslVersions by lazy {
            (0 until NUM_SHADING_LANGUAGE_VERSIONS).map { (gl as GL2).getStringi(GL_SHADING_LANGUAGE_VERSION, it) }
        }

        @JvmField val glsl100 = glslVersions.contains("100")
        @JvmField val glsl110 = glslVersions.contains("110")
        @JvmField val glsl120 = glslVersions.contains("120")
        @JvmField val glsl130 = glslVersions.contains("130")
        @JvmField val glsl140 = glslVersions.contains("140")
        @JvmField val glsl150Core = glslVersions.contains("150 core")
        @JvmField val glsl150Comp = glslVersions.contains("150 compatibility")
        @JvmField val glsl300ES = glslVersions.contains("300 es")
        @JvmField val glsl330Core = glslVersions.contains("330 core")
        @JvmField val glsl330Comp = glslVersions.contains("330 compatibility")
        @JvmField val glsl400Core = glslVersions.contains("400 core")
        @JvmField val glsl400Comp = glslVersions.contains("400 compatibility")
        @JvmField val glsl410Core = glslVersions.contains("410 core")
        @JvmField val glsl410Comp = glslVersions.contains("410 compatibility")
        @JvmField val glsl420Core = glslVersions.contains("420 core")
        @JvmField val glsl420Comp = glslVersions.contains("420 compatibility")
        @JvmField val glsl430Core = glslVersions.contains("430 core")
        @JvmField val glsl430Comp = glslVersions.contains("430 compatibility")
        @JvmField val glsl440Core = glslVersions.contains("440 core")
        @JvmField val glsl440Comp = glslVersions.contains("440 compatibility")
        @JvmField val glsl450Core = glslVersions.contains("450 core")
        @JvmField val glsl450Comp = glslVersions.contains("450 cocompatibilityre")

        fun check(majorVersionRequire: Int, minorVersionRequire: Int) =
                MAJOR_VERSION * 100 + MINOR_VERSION * 10 >= (majorVersionRequire * 100 + minorVersionRequire * 10)
    }

    inner class Extensions(gl: GL) {

        val list: List<String> by lazy { gl.glGetString(GL_EXTENSIONS).split("\\s+".toRegex()) }

        @JvmField val ARB_multitexture = list.contains("GL_ARB_multitexture")
        @JvmField val ARB_transpose_matrix = list.contains("GL_ARB_transpose_matrix")
        @JvmField val ARB_multisample = list.contains("GL_ARB_multisample")
        @JvmField val ARB_texture_env_add = list.contains("GL_ARB_texture_env_add")
        @JvmField val ARB_texture_cube_map = list.contains("GL_ARB_texture_cube_map")
        @JvmField val ARB_texture_compression = list.contains("GL_ARB_texture_compression")
        @JvmField val ARB_texture_border_clamp = list.contains("GL_ARB_texture_border_clamp")
        @JvmField val ARB_point_parameters = list.contains("GL_ARB_point_parameters")
        @JvmField val ARB_vertex_blend = list.contains("GL_ARB_vertex_blend")
        @JvmField val ARB_matrix_palette = list.contains("GL_ARB_matrix_palette")
        @JvmField val ARB_texture_env_combine = list.contains("GL_ARB_texture_env_combine")
        @JvmField val ARB_texture_env_crossbar = list.contains("GL_ARB_texture_env_crossbar")
        @JvmField val ARB_texture_env_dot3 = list.contains("GL_ARB_texture_env_dot3")
        @JvmField val ARB_texture_mirrored_repeat = list.contains("GL_ARB_texture_mirrored_repeat")
        @JvmField val ARB_depth_texture = list.contains("GL_ARB_depth_texture")
        @JvmField val ARB_shadow = list.contains("GL_ARB_shadow")
        @JvmField val ARB_shadow_ambient = list.contains("GL_ARB_shadow_ambient")
        @JvmField val ARB_window_pos = list.contains("GL_ARB_window_pos")
        @JvmField val ARB_vertex_program = list.contains("GL_ARB_vertex_program")
        @JvmField val ARB_fragment_program = list.contains("GL_ARB_fragment_program")
        @JvmField val ARB_vertex_buffer_object = list.contains("GL_ARB_vertex_buffer_object")
        @JvmField val ARB_occlusion_query = list.contains("GL_ARB_occlusion_query")
        @JvmField val ARB_shader_objects = list.contains("GL_ARB_shader_objects")
        @JvmField val ARB_vertex_shader = list.contains("GL_ARB_vertex_shader")
        @JvmField val ARB_fragment_shader = list.contains("GL_ARB_fragment_shader")
        @JvmField val ARB_shading_language_100 = list.contains("GL_ARB_shading_language_100")
        @JvmField val ARB_texture_non_power_of_two = list.contains("GL_ARB_texture_non_power_of_two")
        @JvmField val ARB_point_sprite = list.contains("GL_ARB_point_sprite")
        @JvmField val ARB_fragment_program_shadow = list.contains("GL_ARB_fragment_program_shadow")
        @JvmField val ARB_draw_buffers = list.contains("GL_ARB_draw_buffers")
        @JvmField val ARB_texture_rectangle = list.contains("GL_ARB_texture_rectangle")
        @JvmField val ARB_color_buffer_float = list.contains("GL_ARB_color_buffer_float")
        @JvmField val ARB_half_float_pixel = list.contains("GL_ARB_half_float_pixel")
        @JvmField val ARB_texture_float = list.contains("GL_ARB_texture_float")
        @JvmField val ARB_pixel_buffer_object = list.contains("GL_ARB_pixel_buffer_object")
        @JvmField val ARB_depth_buffer_float = list.contains("GL_ARB_depth_buffer_float")
        @JvmField val ARB_draw_instanced = list.contains("GL_ARB_draw_instanced")
        @JvmField val ARB_framebuffer_object = list.contains("GL_ARB_framebuffer_object")
        @JvmField val ARB_framebuffer_sRGB = list.contains("GL_ARB_framebuffer_sRGB")
        @JvmField val ARB_geometry_shader4 = list.contains("GL_ARB_geometry_shader4")
        @JvmField val ARB_half_float_vertex = list.contains("GL_ARB_half_float_vertex")
        @JvmField val ARB_instanced_arrays = list.contains("GL_ARB_instanced_arrays")
        @JvmField val ARB_map_buffer_range = list.contains("GL_ARB_map_buffer_range")
        @JvmField val ARB_texture_buffer_object = list.contains("GL_ARB_texture_buffer_object")
        @JvmField val ARB_texture_compression_rgtc = list.contains("GL_ARB_texture_compression_rgtc")
        @JvmField val ARB_texture_rg = list.contains("GL_ARB_texture_rg")
        @JvmField val ARB_vertex_array_object = list.contains("GL_ARB_vertex_array_object")
        @JvmField val ARB_uniform_buffer_object = list.contains("GL_ARB_uniform_buffer_object")
        @JvmField val ARB_compatibility = list.contains("GL_ARB_compatibility")
        @JvmField val ARB_copy_buffer = list.contains("GL_ARB_copy_buffer")
        @JvmField val ARB_shader_texture_lod = list.contains("GL_ARB_shader_texture_lod")
        @JvmField val ARB_depth_clamp = list.contains("GL_ARB_depth_clamp")
        @JvmField val ARB_draw_elements_base_vertex = list.contains("GL_ARB_draw_elements_base_vertex")
        @JvmField val ARB_fragment_coord_conventions = list.contains("GL_ARB_fragment_coord_conventions")
        @JvmField val ARB_provoking_vertex = list.contains("GL_ARB_provoking_vertex")
        @JvmField val ARB_seamless_cube_map = list.contains("GL_ARB_seamless_cube_map")
        @JvmField val ARB_sync = list.contains("GL_ARB_sync")
        @JvmField val ARB_texture_multisample = list.contains("GL_ARB_texture_multisample")
        @JvmField val ARB_vertex_array_bgra = list.contains("GL_ARB_vertex_array_bgra")
        @JvmField val ARB_draw_buffers_blend = list.contains("GL_ARB_draw_buffers_blend")
        @JvmField val ARB_sample_shading = list.contains("GL_ARB_sample_shading")
        @JvmField val ARB_texture_cube_map_array = list.contains("GL_ARB_texture_cube_map_array")
        @JvmField val ARB_texture_gather = list.contains("GL_ARB_texture_gather")
        @JvmField val ARB_texture_query_lod = list.contains("GL_ARB_texture_query_lod")
        @JvmField val ARB_shading_language_include = list.contains("GL_ARB_shading_language_include")
        @JvmField val ARB_texture_compression_bptc = list.contains("GL_ARB_texture_compression_bptc")
        @JvmField val ARB_blend_func_extended = list.contains("GL_ARB_blend_func_extended")
        @JvmField val ARB_explicit_attrib_location = list.contains("GL_ARB_explicit_attrib_location")
        @JvmField val ARB_occlusion_query2 = list.contains("GL_ARB_occlusion_query2")
        @JvmField val ARB_sampler_objects = list.contains("GL_ARB_sampler_objects")
        @JvmField val ARB_shader_bit_encoding = list.contains("GL_ARB_shader_bit_encoding")
        @JvmField val ARB_texture_rgb10_a2ui = list.contains("GL_ARB_texture_rgb10_a2ui")
        @JvmField val ARB_texture_swizzle = list.contains("GL_ARB_texture_swizzle")
        @JvmField val ARB_timer_query = list.contains("GL_ARB_timer_query")
        @JvmField val ARB_vertex_type_2_10_10_10_rev = list.contains("GL_ARB_vertex_type_2_10_10_10_rev")
        @JvmField val ARB_draw_indirect = list.contains("GL_ARB_draw_indirect")
        @JvmField val ARB_gpu_shader5 = list.contains("GL_ARB_gpu_shader5")
        @JvmField val ARB_gpu_shader_fp64 = list.contains("GL_ARB_gpu_shader_fp64")
        @JvmField val ARB_shader_subroutine = list.contains("GL_ARB_shader_subroutine")
        @JvmField val ARB_tessellation_shader = list.contains("GL_ARB_tessellation_shader")
        @JvmField val ARB_texture_buffer_object_rgb32 = list.contains("GL_ARB_texture_buffer_object_rgb32")
        @JvmField val ARB_transform_feedback2 = list.contains("GL_ARB_transform_feedback2")
        @JvmField val ARB_transform_feedback3 = list.contains("GL_ARB_transform_feedback3")
        @JvmField val ARB_ES2_compatibility = list.contains("GL_ARB_ES2_compatibility")
        @JvmField val ARB_get_program_binary = list.contains("GL_ARB_get_program_binary")
        @JvmField val ARB_separate_shader_objects = list.contains("GL_ARB_separate_shader_objects")
        @JvmField val ARB_shader_precision = list.contains("GL_ARB_shader_precision")
        @JvmField val ARB_vertex_attrib_64bit = list.contains("GL_ARB_vertex_attrib_64bit")
        @JvmField val ARB_viewport_array = list.contains("GL_ARB_viewport_array")
        @JvmField val ARB_cl_event = list.contains("GL_ARB_cl_event")
        @JvmField val ARB_debug_output = list.contains("GL_ARB_debug_output")
        @JvmField val ARB_robustness = list.contains("GL_ARB_robustness")
        @JvmField val ARB_shader_stencil_export = list.contains("GL_ARB_shader_stencil_export")
        @JvmField val ARB_base_instance = list.contains("GL_ARB_base_instance")
        @JvmField val ARB_shading_language_420pack = list.contains("GL_ARB_shading_language_420pack")
        @JvmField val ARB_transform_feedback_instanced = list.contains("GL_ARB_transform_feedback_instanced")
        @JvmField val ARB_compressed_texture_pixel_storage = list.contains("GL_ARB_compressed_texture_pixel_storage")
        @JvmField val ARB_conservative_depth = list.contains("GL_ARB_conservative_depth")
        @JvmField val ARB_internalformat_query = list.contains("GL_ARB_internalformat_query")
        @JvmField val ARB_map_buffer_alignment = list.contains("GL_ARB_map_buffer_alignment")
        @JvmField val ARB_shader_atomic_counters = list.contains("GL_ARB_shader_atomic_counters")
        @JvmField val ARB_shader_image_load_store = list.contains("GL_ARB_shader_image_load_store")
        @JvmField val ARB_shading_language_packing = list.contains("GL_ARB_shading_language_packing")
        @JvmField val ARB_texture_storage = list.contains("GL_ARB_texture_storage")
        @JvmField val KHR_texture_compression_astc_hdr = list.contains("GL_KHR_texture_compression_astc_hdr")
        @JvmField val KHR_texture_compression_astc_ldr = list.contains("GL_KHR_texture_compression_astc_ldr")
        @JvmField val KHR_debug = list.contains("GL_KHR_debug")
        @JvmField val ARB_arrays_of_arrays = list.contains("GL_ARB_arrays_of_arrays")
        @JvmField val ARB_clear_buffer_object = list.contains("GL_ARB_clear_buffer_object")
        @JvmField val ARB_compute_shader = list.contains("GL_ARB_compute_shader")
        @JvmField val ARB_copy_image = list.contains("GL_ARB_copy_image")
        @JvmField val ARB_texture_view = list.contains("GL_ARB_texture_view")
        @JvmField val ARB_vertex_attrib_binding = list.contains("GL_ARB_vertex_attrib_binding")
        @JvmField val ARB_robustness_isolation = list.contains("GL_ARB_robustness_isolation")
        @JvmField val ARB_ES3_compatibility = list.contains("GL_ARB_ES3_compatibility")
        @JvmField val ARB_explicit_uniform_location = list.contains("GL_ARB_explicit_uniform_location")
        @JvmField val ARB_fragment_layer_viewport = list.contains("GL_ARB_fragment_layer_viewport")
        @JvmField val ARB_framebuffer_no_attachments = list.contains("GL_ARB_framebuffer_no_attachments")
        @JvmField val ARB_internalformat_query2 = list.contains("GL_ARB_internalformat_query2")
        @JvmField val ARB_invalidate_subdata = list.contains("GL_ARB_invalidate_subdata")
        @JvmField val ARB_multi_draw_indirect = list.contains("GL_ARB_multi_draw_indirect")
        @JvmField val ARB_program_interface_query = list.contains("GL_ARB_program_interface_query")
        @JvmField val ARB_robust_buffer_access_behavior = list.contains("GL_ARB_robust_buffer_access_behavior")
        @JvmField val ARB_shader_image_size = list.contains("GL_ARB_shader_image_size")
        @JvmField val ARB_shader_storage_buffer_object = list.contains("GL_ARB_shader_storage_buffer_object")
        @JvmField val ARB_stencil_texturing = list.contains("GL_ARB_stencil_texturing")
        @JvmField val ARB_texture_buffer_range = list.contains("GL_ARB_texture_buffer_range")
        @JvmField val ARB_texture_query_levels = list.contains("GL_ARB_texture_query_levels")
        @JvmField val ARB_texture_storage_multisample = list.contains("GL_ARB_texture_storage_multisample")
        @JvmField val ARB_buffer_storage = list.contains("GL_ARB_buffer_storage")
        @JvmField val ARB_clear_texture = list.contains("GL_ARB_clear_texture")
        @JvmField val ARB_enhanced_layouts = list.contains("GL_ARB_enhanced_layouts")
        @JvmField val ARB_multi_bind = list.contains("GL_ARB_multi_bind")
        @JvmField val ARB_query_buffer_object = list.contains("GL_ARB_query_buffer_object")
        @JvmField val ARB_texture_mirror_clamp_to_edge = list.contains("GL_ARB_texture_mirror_clamp_to_edge")
        @JvmField val ARB_texture_stencil8 = list.contains("GL_ARB_texture_stencil8")
        @JvmField val ARB_vertex_type_10f_11f_11f_rev = list.contains("GL_ARB_vertex_type_10f_11f_11f_rev")
        @JvmField val ARB_bindless_texture = list.contains("GL_ARB_bindless_texture")
        @JvmField val ARB_compute_valiable_group_size = list.contains("GL_ARB_compute_valiable_group_size")
        @JvmField val ARB_indirect_parameters = list.contains("GL_ARB_indirect_parameters")
        @JvmField val ARB_seamless_cubemap_per_texture = list.contains("GL_ARB_seamless_cubemap_per_texture")
        @JvmField val ARB_shader_draw_parameters = list.contains("GL_ARB_shader_draw_parameters")
        @JvmField val ARB_shader_group_vote = list.contains("GL_ARB_shader_group_vote")
        @JvmField val ARB_sparse_texture = list.contains("GL_ARB_sparse_texture")
        @JvmField val ARB_ES3_1_compatibility = list.contains("GL_ARB_ES3_1_compatibility")
        @JvmField val ARB_clip_control = list.contains("GL_ARB_clip_control")
        @JvmField val ARB_conditional_render_inverted = list.contains("GL_ARB_conditional_render")
        @JvmField val ARB_cull_distance = list.contains("GL_ARB_cull_distance")
        @JvmField val ARB_derivative_control = list.contains("GL_ARB_derivative_control")
        @JvmField val ARB_direct_state_access = list.contains("GL_ARB_direct_state_access")
        @JvmField val ARB_get_texture_sub_image = list.contains("GL_ARB_get_texture_sub_image")
        @JvmField val ARB_shader_texture_image_samples = list.contains("GL_ARB_shader_texture_image_samples")
        @JvmField val ARB_texture_barrier = list.contains("GL_ARB_texture_barrier")
        @JvmField val KHR_context_flush_control = list.contains("GL_KHR_context_flush_control")
        @JvmField val KHR_robust_buffer_access_behavior = list.contains("GL_KHR_robust_buffer_access_behavior")
        @JvmField val KHR_robustness = list.contains("GL_KHR_robustness")
        @JvmField val ARB_pipeline_statistics_query = list.contains("GL_ARB_pipeline_statistics_query")
        @JvmField val ARB_sparse_buffer = list.contains("GL_ARB_sparse_buffer")
        @JvmField val ARB_transform_feedback_overflow_query = list.contains("GL_ARB_transform_feedback_overflow_query")

        // EXT
        @JvmField val EXT_texture_compression_latc = list.contains("GL_EXT_texture_compression_latc")
        @JvmField val EXT_transform_feedback = list.contains("GL_EXT_transform_feedback")
        @JvmField val EXT_direct_state_access = list.contains("GL_EXT_direct_state_access")
        @JvmField val EXT_texture_filter_anisotropic = list.contains("GL_EXT_texture_filter_anisotropic")
        @JvmField val EXT_texture_compression_s3tc = list.contains("GL_EXT_texture_compression_s3tc")
        @JvmField val EXT_texture_array = list.contains("GL_EXT_texture_array")
        @JvmField val EXT_texture_snorm = list.contains("GL_EXT_texture_snorm")
        @JvmField val EXT_texture_sRGB_decode = list.contains("GL_EXT_texture_sRGB_decode")
        @JvmField val EXT_framebuffer_multisample_blit_scaled = list.contains("GL_EXT_framebuffer_multisample_blit_scaled")
        @JvmField val EXT_shader_integer_mix = list.contains("GL_EXT_shader_integer_mix")
        @JvmField val EXT_shader_image_load_formatted = list.contains("GL_EXT_shader_image_load_formatted")
        @JvmField val EXT_polygon_offset_clamp = list.contains("GL_EXT_polygon_offset_clamp")

        // NV
        @JvmField val NV_explicit_multisample = list.contains("GL_NV_explicit_multisample")
        @JvmField val NV_shader_buffer_load = list.contains("GL_NV_shader_buffer_load")
        @JvmField val NV_vertex_buffer_unified_memory = list.contains("GL_NV_vertex_buffer_unified_memory")
        @JvmField val NV_shader_buffer_store = list.contains("GL_NV_shader_buffer_store")
        @JvmField val NV_bindless_multi_draw_indirect = list.contains("GL_NV_bindless_multi_draw_indirect")
        @JvmField val NV_blend_equation_advanced = list.contains("GL_NV_blend_equation_advanced")
        @JvmField val NV_deep_texture3D = list.contains("GL_NV_deep_texture3D")
        @JvmField val NV_shader_thread_group = list.contains("GL_NV_shader_thread_group")
        @JvmField val NV_shader_thread_shuffle = list.contains("GL_NV_shader_thread_shuffle")
        @JvmField val NV_shader_atomic_int64 = list.contains("GL_NV_shader_atomic_int64")
        @JvmField val NV_bindless_multi_draw_indirect_count = list.contains("GL_NV_bindless_multi_draw_indirect_count")
        @JvmField val NV_uniform_buffer_unified_memory = list.contains("GL_NV_uniform_buffer_unified_memory")

        // AMD
        @JvmField val ATI_texture_compression_3dc = list.contains("GL_ATI_texture_compression_3dc")
        @JvmField val AMD_depth_clamp_separate = list.contains("GL_AMD_depth_clamp_separate")
        @JvmField val AMD_stencil_operation_extended = list.contains("GL_AMD_stencil_operation_extended")
        @JvmField val AMD_vertex_shader_viewport_index = list.contains("GL_AMD_vertex_shader_viewport_index")
        @JvmField val AMD_vertex_shader_layer = list.contains("GL_AMD_vertex_shader_layer")
        @JvmField val AMD_shader_trinary_minmax = list.contains("GL_AMD_shader_trinary_minmax")
        @JvmField val AMD_interleaved_elements = list.contains("GL_AMD_interleaved_elements")
        @JvmField val AMD_shader_atomic_counter_ops = list.contains("GL_AMD_shader_atomic_counter_ops")
        @JvmField val AMD_occlusion_query_event = list.contains("GL_AMD_occlusion_query_event")
        @JvmField val AMD_shader_stencil_value_export = list.contains("GL_AMD_shader_stencil_value_export")
        @JvmField val AMD_transform_feedback4 = list.contains("GL_AMD_transform_feedback4")
        @JvmField val AMD_gpu_shader_int64 = list.contains("GL_AMD_gpu_shader_int64")
        @JvmField val AMD_gcn_shader = list.contains("GL_AMD_gcn_shader")

        // Intel
        @JvmField val INTEL_map_texture = list.contains("GL_INTEL_map_texture")
        @JvmField val INTEL_fragment_shader_ordering = list.contains("GL_INTEL_fragment_shader_ordering")
        @JvmField val INTEL_performance_query = list.contains("GL_INTEL_performance_query")
    }

    inner class Debug(gl: GL) {
        @JvmField val CONTEXT_FLAGS = gl.getInteger(GL_CONTEXT_FLAGS)
        @JvmField val MAX_DEBUG_GROUP_STACK_DEPTH = gl.getInteger(GL_MAX_DEBUG_GROUP_STACK_DEPTH)
        @JvmField val MAX_LABEL_LENGTH = gl.getInteger(GL_MAX_LABEL_LENGTH)
        @JvmField val MAX_SERVER_WAIT_TIMEOUT = gl.getInteger(GL_MAX_SERVER_WAIT_TIMEOUT)
    }

    inner class Limits(gl: GL) {

        @JvmField var MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_COMPUTE_UNIFORM_BLOCKS = 0
        @JvmField var MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_COMPUTE_IMAGE_UNIFORMS = 0
        @JvmField var MAX_COMPUTE_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_COMPUTE_ATOMIC_COUNTERS = 0
        @JvmField var MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 0
        @JvmField var MAX_COMPUTE_SHARED_MEMORY_SIZE = 0
        @JvmField var MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 0
        @JvmField var MAX_COMPUTE_WORK_GROUP_COUNT = 0
        @JvmField var MAX_COMPUTE_WORK_GROUP_SIZE = 0

        @JvmField var MAX_VERTEX_ATOMIC_COUNTERS = 0
        @JvmField var MAX_VERTEX_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_VERTEX_ATTRIBS = 0
        @JvmField var MAX_VERTEX_OUTPUT_COMPONENTS = 0
        @JvmField var MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_VERTEX_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_VERTEX_UNIFORM_VECTORS = 0
        @JvmField var MAX_VERTEX_UNIFORM_BLOCKS = 0
        @JvmField var MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0

        @JvmField var MAX_TESS_CONTROL_ATOMIC_COUNTERS = 0
        @JvmField var MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_TESS_CONTROL_INPUT_COMPONENTS = 0
        @JvmField var MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 0
        @JvmField var MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_TESS_CONTROL_UNIFORM_BLOCKS = 0
        @JvmField var MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 0

        @JvmField var MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 0
        @JvmField var MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_TESS_EVALUATION_INPUT_COMPONENTS = 0
        @JvmField var MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 0
        @JvmField var MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 0
        @JvmField var MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 0

        @JvmField var MAX_GEOMETRY_ATOMIC_COUNTERS = 0
        @JvmField var MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_GEOMETRY_INPUT_COMPONENTS = 0
        @JvmField var MAX_GEOMETRY_OUTPUT_COMPONENTS = 0
        @JvmField var MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_GEOMETRY_UNIFORM_BLOCKS = 0
        @JvmField var MAX_GEOMETRY_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_VERTEX_STREAMS = 0

        @JvmField var MAX_FRAGMENT_ATOMIC_COUNTERS = 0
        @JvmField var MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_FRAGMENT_INPUT_COMPONENTS = 0
        @JvmField var MAX_FRAGMENT_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_FRAGMENT_UNIFORM_VECTORS = 0
        @JvmField var MAX_FRAGMENT_UNIFORM_BLOCKS = 0
        @JvmField var MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0
        @JvmField var MAX_DRAW_BUFFERS = 0
        @JvmField var MAX_DUAL_SOURCE_DRAW_BUFFERS = 0

        @JvmField var MAX_COLOR_ATTACHMENTS = 0
        @JvmField var MAX_FRAMEBUFFER_WIDTH = 0
        @JvmField var MAX_FRAMEBUFFER_HEIGHT = 0
        @JvmField var MAX_FRAMEBUFFER_LAYERS = 0
        @JvmField var MAX_FRAMEBUFFER_SAMPLES = 0
        @JvmField var MAX_SAMPLE_MASK_WORDS = 0

        @JvmField var MAX_TRANSFORM_FEEDBACK_BUFFERS = 0
        @JvmField var MIN_MAP_BUFFER_ALIGNMENT = 0

        @JvmField var MAX_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0
        @JvmField var MAX_RECTANGLE_TEXTURE_SIZE = 0
        @JvmField var MAX_DEEP_3D_TEXTURE_WIDTH_HEIGHT_NV = 0
        @JvmField var MAX_DEEP_3D_TEXTURE_DEPTH_NV = 0
        @JvmField var MAX_COLOR_TEXTURE_SAMPLES = 0
        @JvmField var MAX_DEPTH_TEXTURE_SAMPLES = 0
        @JvmField var MAX_INTEGER_SAMPLES = 0
        @JvmField var MAX_TEXTURE_BUFFER_SIZE = 0
        val NUM_COMPRESSED_TEXTURE_FORMATS = gl.getInteger(GL_NUM_COMPRESSED_TEXTURE_FORMATS)
        @JvmField var MAX_TEXTURE_MAX_ANISOTROPY_EXT = 0

        @JvmField var MAX_PATCH_VERTICES = 0
        @JvmField var MAX_TESS_GEN_LEVEL = 0
        @JvmField var MAX_SUBROUTINES = 0
        @JvmField var MAX_SUBROUTINE_UNIFORM_LOCATIONS = 0
        @JvmField var MAX_COMBINED_ATOMIC_COUNTERS = 0
        @JvmField var MAX_COMBINED_SHADER_STORAGE_BLOCKS = 0
        @JvmField var MAX_PROGRAM_TEXEL_OFFSET = 0
        @JvmField var MIN_PROGRAM_TEXEL_OFFSET = 0
        @JvmField var MAX_COMBINED_UNIFORM_BLOCKS = 0
        @JvmField var MAX_UNIFORM_BUFFER_BINDINGS = 0
        @JvmField var MAX_UNIFORM_BLOCK_SIZE = 0
        @JvmField var MAX_UNIFORM_LOCATIONS = 0
        @JvmField var MAX_VARYING_COMPONENTS = 0
        @JvmField var MAX_VARYING_VECTORS = 0
        @JvmField var MAX_VARYING_FLOATS = 0
        @JvmField var MAX_SHADER_STORAGE_BUFFER_BINDINGS = 0
        @JvmField var MAX_SHADER_STORAGE_BLOCK_SIZE = 0
        @JvmField var MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0
        @JvmField var SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 0
        @JvmField var UNIFORM_BUFFER_OFFSET_ALIGNMENT = 0
        @JvmField var NUM_PROGRAM_BINARY_FORMATS = 0
        @JvmField var PROGRAM_BINARY_FORMATS = 0
        @JvmField var NUM_SHADER_BINARY_FORMATS = 0
        @JvmField var SHADER_BINARY_FORMATS = 0 //TODO

        init {

            with(gl) {

                if (check(4, 3) || extensions.ARB_compute_shader) {
                    MAX_COMPUTE_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS)
                    MAX_COMPUTE_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMPUTE_UNIFORM_COMPONENTS)
                    MAX_COMPUTE_SHARED_MEMORY_SIZE = getInteger(GL_MAX_COMPUTE_SHARED_MEMORY_SIZE)
                    MAX_COMPUTE_WORK_GROUP_INVOCATIONS = getInteger(GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS)
                    MAX_COMPUTE_WORK_GROUP_COUNT = (this as GL2).getIntegeri(GL_MAX_COMPUTE_WORK_GROUP_COUNT, 0)
                    MAX_COMPUTE_WORK_GROUP_SIZE = this.getIntegeri(GL_MAX_COMPUTE_WORK_GROUP_SIZE, 0)
                }

                if (check(4, 3) || (extensions.ARB_compute_shader && extensions.ARB_uniform_buffer_object)) {
                    MAX_COMPUTE_UNIFORM_BLOCKS = getInteger(GL_MAX_COMPUTE_UNIFORM_BLOCKS)
                    MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS)
                }

                if (check(4, 3) || (extensions.ARB_compute_shader && extensions.ARB_shader_image_load_store))
                    MAX_COMPUTE_IMAGE_UNIFORMS = getInteger(GL_MAX_COMPUTE_IMAGE_UNIFORMS)

                if (check(4, 3) || (extensions.ARB_compute_shader && extensions.ARB_shader_atomic_counters)) {
                    MAX_COMPUTE_ATOMIC_COUNTERS = getInteger(GL_MAX_COMPUTE_ATOMIC_COUNTERS)
                    MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = getInteger(GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS)
                }

                if (check(4, 3) || (extensions.ARB_compute_shader && extensions.ARB_shader_storage_buffer_object))
                    MAX_COMPUTE_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS)


                if (check(2, 1) || extensions.ARB_vertex_shader) {
                    MAX_VERTEX_ATTRIBS = getInteger(GL_MAX_VERTEX_ATTRIBS)
                    MAX_VERTEX_OUTPUT_COMPONENTS = getInteger(GL_MAX_VERTEX_OUTPUT_COMPONENTS)
                    MAX_VERTEX_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS)
                    MAX_VERTEX_UNIFORM_COMPONENTS = getInteger(GL_MAX_VERTEX_UNIFORM_COMPONENTS)
                    MAX_VERTEX_UNIFORM_VECTORS = getInteger(GL_MAX_VERTEX_UNIFORM_VECTORS)
                }
                if (check(3, 2) || (extensions.ARB_vertex_shader && extensions.ARB_uniform_buffer_object)) {
                    MAX_VERTEX_UNIFORM_BLOCKS = getInteger(GL_MAX_VERTEX_UNIFORM_BLOCKS)
                    MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS)
                }
                if (check(4, 2) || (extensions.ARB_vertex_shader && extensions.ARB_shader_atomic_counters))
                    MAX_VERTEX_ATOMIC_COUNTERS = getInteger(GL_MAX_VERTEX_ATOMIC_COUNTERS)
                if (check(4, 3) || (extensions.ARB_vertex_shader && extensions.ARB_shader_storage_buffer_object))
                    MAX_VERTEX_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS)

                if (check(4, 0) || extensions.ARB_tessellation_shader) {
                    MAX_TESS_CONTROL_INPUT_COMPONENTS = getInteger(GL_MAX_TESS_CONTROL_INPUT_COMPONENTS)
                    MAX_TESS_CONTROL_OUTPUT_COMPONENTS = getInteger(GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS)
                    MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS)
                    MAX_TESS_CONTROL_UNIFORM_COMPONENTS = getInteger(GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS)
                }
                if (check(4, 0) || (extensions.ARB_tessellation_shader && extensions.ARB_uniform_buffer_object)) {
                    MAX_TESS_CONTROL_UNIFORM_BLOCKS = getInteger(GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS)
                    MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS)
                }
                if (check(4, 2) || (extensions.ARB_tessellation_shader && extensions.ARB_shader_atomic_counters))
                    MAX_TESS_CONTROL_ATOMIC_COUNTERS = getInteger(GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS)
                if (check(4, 3) || (extensions.ARB_tessellation_shader && extensions.ARB_shader_storage_buffer_object))
                    MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS)

                if (check(4, 0) || extensions.ARB_tessellation_shader) {
                    MAX_TESS_EVALUATION_INPUT_COMPONENTS = getInteger(GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS)
                    MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = getInteger(GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS)
                    MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS)
                    MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = getInteger(GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS)
                }
                if (check(4, 0) || (extensions.ARB_tessellation_shader && extensions.ARB_uniform_buffer_object)) {
                    MAX_TESS_EVALUATION_UNIFORM_BLOCKS = getInteger(GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS)
                    MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS)
                }
                if (check(4, 2) || (extensions.ARB_tessellation_shader && extensions.ARB_shader_atomic_counters))
                    MAX_TESS_EVALUATION_ATOMIC_COUNTERS = getInteger(GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS)
                if (check(4, 3) || (extensions.ARB_tessellation_shader && extensions.ARB_shader_storage_buffer_object))
                    MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS)

                if (check(3, 2) || extensions.ARB_geometry_shader4) {
                    MAX_GEOMETRY_INPUT_COMPONENTS = getInteger(GL_MAX_GEOMETRY_INPUT_COMPONENTS)
                    MAX_GEOMETRY_OUTPUT_COMPONENTS = getInteger(GL_MAX_GEOMETRY_OUTPUT_COMPONENTS)
                    MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS)
                    MAX_GEOMETRY_UNIFORM_COMPONENTS = getInteger(GL_MAX_GEOMETRY_UNIFORM_COMPONENTS)
                }
                if (check(3, 2) || (extensions.ARB_geometry_shader4 && extensions.ARB_uniform_buffer_object)) {
                    MAX_GEOMETRY_UNIFORM_BLOCKS = getInteger(GL_MAX_GEOMETRY_UNIFORM_BLOCKS)
                    MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS)
                }
                if (check(4, 0) || (extensions.ARB_geometry_shader4 && extensions.ARB_transform_feedback3))
                    MAX_VERTEX_STREAMS = getInteger(GL_MAX_VERTEX_STREAMS)
                if (check(4, 2) || (extensions.ARB_geometry_shader4 && extensions.ARB_shader_atomic_counters))
                    MAX_GEOMETRY_ATOMIC_COUNTERS = getInteger(GL_MAX_GEOMETRY_ATOMIC_COUNTERS)
                if (check(4, 3) || (extensions.ARB_geometry_shader4 && extensions.ARB_shader_storage_buffer_object))
                    MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS)

                if (check(2, 1))
                    MAX_DRAW_BUFFERS = getInteger(GL_MAX_DRAW_BUFFERS)

                if (check(2, 1) || extensions.ARB_fragment_shader) {
                    MAX_FRAGMENT_INPUT_COMPONENTS = getInteger(GL_MAX_FRAGMENT_INPUT_COMPONENTS)
                    MAX_FRAGMENT_UNIFORM_COMPONENTS = getInteger(GL_MAX_FRAGMENT_UNIFORM_COMPONENTS)
                    MAX_FRAGMENT_UNIFORM_VECTORS = getInteger(GL_MAX_FRAGMENT_UNIFORM_VECTORS)
                }
                if (check(3, 2) || (extensions.ARB_fragment_shader && extensions.ARB_uniform_buffer_object)) {
                    MAX_FRAGMENT_UNIFORM_BLOCKS = getInteger(GL_MAX_FRAGMENT_UNIFORM_BLOCKS)
                    MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = getInteger(GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS)
                }
                if (check(3, 3) || extensions.ARB_blend_func_extended)
                    MAX_DUAL_SOURCE_DRAW_BUFFERS = getInteger(GL_MAX_DUAL_SOURCE_DRAW_BUFFERS)
                if (check(4, 2) || (extensions.ARB_fragment_shader && extensions.ARB_shader_atomic_counters))
                    MAX_FRAGMENT_ATOMIC_COUNTERS = getInteger(GL_MAX_FRAGMENT_ATOMIC_COUNTERS)
                if (check(4, 3) || (extensions.ARB_fragment_shader && extensions.ARB_shader_storage_buffer_object))
                    MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS)

                if (check(3, 0) || extensions.ARB_framebuffer_object)
                    MAX_COLOR_ATTACHMENTS = getInteger(GL_MAX_COLOR_ATTACHMENTS)

                if (check(4, 3) || extensions.ARB_framebuffer_no_attachments) {
                    MAX_FRAMEBUFFER_HEIGHT = getInteger(GL_MAX_FRAMEBUFFER_HEIGHT)
                    MAX_FRAMEBUFFER_WIDTH = getInteger(GL_MAX_FRAMEBUFFER_WIDTH)
                    MAX_FRAMEBUFFER_LAYERS = getInteger(GL_MAX_FRAMEBUFFER_LAYERS)
                    MAX_FRAMEBUFFER_SAMPLES = getInteger(GL_MAX_FRAMEBUFFER_SAMPLES)
                }

                if (check(4, 0) || extensions.ARB_transform_feedback3)
                    MAX_TRANSFORM_FEEDBACK_BUFFERS = getInteger(GL_MAX_TRANSFORM_FEEDBACK_BUFFERS)
                if (check(4, 2) || extensions.ARB_map_buffer_alignment)
                    MIN_MAP_BUFFER_ALIGNMENT = getInteger(GL_MIN_MAP_BUFFER_ALIGNMENT)

                if (extensions.NV_deep_texture3D) {
                    MAX_DEEP_3D_TEXTURE_WIDTH_HEIGHT_NV = getInteger(GL_MAX_DEEP_3D_TEXTURE_WIDTH_HEIGHT_NV)
                    MAX_DEEP_3D_TEXTURE_DEPTH_NV = getInteger(GL_MAX_DEEP_3D_TEXTURE_DEPTH_NV)
                }

                if (check(2, 1)) {
                    MAX_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_TEXTURE_IMAGE_UNITS)
                    MAX_COMBINED_TEXTURE_IMAGE_UNITS = getInteger(GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS)
                    MAX_TEXTURE_MAX_ANISOTROPY_EXT = getInteger(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT)
                }

                if (check(3, 0) || extensions.ARB_texture_buffer_object)
                    MAX_TEXTURE_BUFFER_SIZE = getInteger(GL_MAX_TEXTURE_BUFFER_SIZE)

                if (check(3, 2) || extensions.ARB_texture_multisample) {
                    MAX_SAMPLE_MASK_WORDS = getInteger(GL_MAX_SAMPLE_MASK_WORDS)
                    MAX_COLOR_TEXTURE_SAMPLES = getInteger(GL_MAX_COLOR_TEXTURE_SAMPLES)
                    MAX_DEPTH_TEXTURE_SAMPLES = getInteger(GL_MAX_DEPTH_TEXTURE_SAMPLES)
                    MAX_INTEGER_SAMPLES = getInteger(GL_MAX_INTEGER_SAMPLES)
                }

                if (check(3, 3) || extensions.ARB_texture_rectangle)
                    MAX_RECTANGLE_TEXTURE_SIZE = getInteger(GL_MAX_RECTANGLE_TEXTURE_SIZE)

                if (check(2, 2) && version.profile == Profile.COMPATIBILITY) {
                    MAX_VARYING_COMPONENTS = getInteger(GL_MAX_VARYING_COMPONENTS)
                    MAX_VARYING_VECTORS = getInteger(GL_MAX_VARYING_VECTORS)
                    MAX_VARYING_FLOATS = getInteger(GL_MAX_VARYING_FLOATS)
                }

                if (check(3, 2)) {
                    MAX_COMBINED_UNIFORM_BLOCKS = getInteger(GL_MAX_COMBINED_UNIFORM_BLOCKS)
                    MAX_UNIFORM_BUFFER_BINDINGS = getInteger(GL_MAX_UNIFORM_BUFFER_BINDINGS)
                    MAX_UNIFORM_BLOCK_SIZE = getInteger(GL_MAX_UNIFORM_BLOCK_SIZE)
                    UNIFORM_BUFFER_OFFSET_ALIGNMENT = getInteger(GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT)
                }

                if (check(4, 0)) {
                    MAX_PATCH_VERTICES = getInteger(GL_MAX_PATCH_VERTICES)
                    MAX_TESS_GEN_LEVEL = getInteger(GL_MAX_TESS_GEN_LEVEL)
                    MAX_SUBROUTINES = getInteger(GL_MAX_SUBROUTINES)
                    MAX_SUBROUTINE_UNIFORM_LOCATIONS = getInteger(GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS)
                    MAX_COMBINED_ATOMIC_COUNTERS = getInteger(GL_MAX_COMBINED_ATOMIC_COUNTERS)
                    MAX_COMBINED_SHADER_STORAGE_BLOCKS = getInteger(GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS)
                    MAX_PROGRAM_TEXEL_OFFSET = getInteger(GL_MAX_PROGRAM_TEXEL_OFFSET)
                    MIN_PROGRAM_TEXEL_OFFSET = getInteger(GL_MIN_PROGRAM_TEXEL_OFFSET)
                }

                if (check(4, 1)) {
                    NUM_PROGRAM_BINARY_FORMATS = getInteger(GL_NUM_PROGRAM_BINARY_FORMATS)
                    NUM_SHADER_BINARY_FORMATS = getInteger(GL_NUM_SHADER_BINARY_FORMATS)
                    PROGRAM_BINARY_FORMATS = getInteger(GL_PROGRAM_BINARY_FORMATS)
                }

                if (check(4, 2)) {
                    MAX_COMBINED_SHADER_OUTPUT_RESOURCES = getInteger(GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES)
                    MAX_SHADER_STORAGE_BUFFER_BINDINGS = getInteger(GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS)
                    MAX_SHADER_STORAGE_BLOCK_SIZE = getInteger(GL_MAX_SHADER_STORAGE_BLOCK_SIZE)
                    MAX_COMBINED_SHADER_OUTPUT_RESOURCES = getInteger(GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES)
                    SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = getInteger(GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT)
                }

                if (check(4, 3))
                    MAX_COMBINED_SHADER_OUTPUT_RESOURCES = getInteger(GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES)

                if (check(4, 3) || extensions.ARB_explicit_uniform_location)
                    MAX_UNIFORM_LOCATIONS = getInteger(GL_MAX_UNIFORM_LOCATIONS)
            }
        }
    }

    inner class Values(gl: GL) {

        @JvmField var SUBPIXEL_BITS = 0
        @JvmField var MAX_CLIP_DISTANCES = 0
        @JvmField var MAX_CULL_DISTANCES = 0
        @JvmField var MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 0
        @JvmField var MAX_ELEMENT_INDEX = 0L
        @JvmField var MAX_ELEMENTS_INDICES = 0
        @JvmField var MAX_ELEMENTS_VERTICES = 0
        @JvmField var IMPLEMENTATION_COLOR_READ_FORMAT = 0
        @JvmField var IMPLEMENTATION_COLOR_READ_TYPE = 0
        @JvmField val PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = gl.getBoolean(GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED)

        @JvmField var MAX_3D_TEXTURE_SIZE = 0
        @JvmField var MAX_TEXTURE_SIZE = 0
        @JvmField var MAX_ARRAY_TEXTURE_LAYERS = 0
        @JvmField var MAX_CUBE_MAP_TEXTURE_SIZE = 0
        @JvmField var MAX_TEXTURE_LOD_BIAS = 0
        @JvmField val MAX_RENDERBUFFER_SIZE = gl.getInteger(GL_MAX_RENDERBUFFER_SIZE)

        @JvmField var MAX_VIEWPORT_DIMS = 0f
        @JvmField var MAX_VIEWPORTS = 0
        @JvmField var VIEWPORT_SUBPIXEL_BITS = 0
        @JvmField var VIEWPORT_BOUNDS_RANGE = Vec2()

        @JvmField var LAYER_PROVOKING_VERTEX = 0
        @JvmField var VIEWPORT_INDEX_PROVOKING_VERTEX = 0

        @JvmField var POINT_SIZE_MAX = 0f
        @JvmField var POINT_SIZE_MIN = 0f
        @JvmField val POINT_SIZE_RANGE = gl.getVec2(GL_POINT_SIZE_RANGE)
        @JvmField val POINT_SIZE_GRANULARITY = gl.getFloat(GL_POINT_SIZE_GRANULARITY)

        @JvmField val ALIASED_LINE_WIDTH_RANGE = gl.getVec2(GL_ALIASED_LINE_WIDTH_RANGE)
        @JvmField val SMOOTH_LINE_WIDTH_RANGE = gl.getVec2(GL_SMOOTH_LINE_WIDTH_RANGE)
        @JvmField val SMOOTH_LINE_WIDTH_GRANULARITY = gl.getFloat(GL_SMOOTH_LINE_WIDTH_GRANULARITY)

        @JvmField var MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0
        @JvmField var MAX_VERTEX_ATTRIB_BINDINGS = 0

        @JvmField var TEXTURE_BUFFER_OFFSET_ALIGNMENT = 0

        init {

            with(gl) {

                if (check(2, 1)) {
                    MAX_ELEMENTS_INDICES = getInteger(GL_MAX_ELEMENTS_INDICES)
                    MAX_ELEMENTS_VERTICES = getInteger(GL_MAX_ELEMENTS_VERTICES)
                }

                if (check(4, 3) || extensions.ARB_vertex_attrib_binding) {
                    MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = getInteger(GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET)
                    MAX_VERTEX_ATTRIB_BINDINGS = getInteger(GL_MAX_VERTEX_ATTRIB_BINDINGS)
                }

                if (check(4, 3) || extensions.ARB_ES3_compatibility)
                    MAX_ELEMENT_INDEX = (this as GL3).getInteger64(GL_MAX_ELEMENT_INDEX)

                if (version.profile == Profile.COMPATIBILITY) {
                    POINT_SIZE_MIN = getFloat(GL_POINT_SIZE_MIN)
                    POINT_SIZE_MAX = getFloat(GL_POINT_SIZE_MAX)
                }


                if (check(2, 1)) {
                    SUBPIXEL_BITS = getInteger(GL_SUBPIXEL_BITS)
                    MAX_VIEWPORT_DIMS = getFloat(GL_MAX_VIEWPORT_DIMS)
                }

                if (check(3, 0)) {
                    MAX_CLIP_DISTANCES = getInteger(GL_MAX_CLIP_DISTANCES)
                }

                if (check(4, 5) || extensions.ARB_cull_distance) {
                    MAX_CULL_DISTANCES = getInteger(GL_MAX_CULL_DISTANCES)
                    MAX_COMBINED_CLIP_AND_CULL_DISTANCES = getInteger(GL_MAX_COMBINED_CLIP_AND_CULL_DISTANCES)
                }

                if (check(4, 1) || extensions.ARB_viewport_array) {
                    MAX_VIEWPORTS = getInteger(GL_MAX_VIEWPORTS)
                    VIEWPORT_SUBPIXEL_BITS = getInteger(GL_VIEWPORT_SUBPIXEL_BITS)
                    VIEWPORT_BOUNDS_RANGE = getVec2(GL_VIEWPORT_BOUNDS_RANGE)
                    LAYER_PROVOKING_VERTEX = getInteger(GL_LAYER_PROVOKING_VERTEX)
                    VIEWPORT_INDEX_PROVOKING_VERTEX = getInteger(GL_VIEWPORT_INDEX_PROVOKING_VERTEX)
                }

                if (check(4, 1) || extensions.ARB_ES2_compatibility) {
                    IMPLEMENTATION_COLOR_READ_FORMAT = getInteger(GL_IMPLEMENTATION_COLOR_READ_FORMAT)
                    IMPLEMENTATION_COLOR_READ_TYPE = getInteger(GL_IMPLEMENTATION_COLOR_READ_TYPE)
                }

                if (check(2, 1)) {
                    MAX_TEXTURE_LOD_BIAS = getInteger(GL_MAX_TEXTURE_LOD_BIAS)
                    MAX_TEXTURE_SIZE = getInteger(GL_MAX_TEXTURE_SIZE)
                    MAX_3D_TEXTURE_SIZE = getInteger(GL_MAX_3D_TEXTURE_SIZE)
                    MAX_CUBE_MAP_TEXTURE_SIZE = getInteger(GL_MAX_CUBE_MAP_TEXTURE_SIZE)
                }

                if (check(3, 0) || extensions.EXT_texture_array)
                    MAX_ARRAY_TEXTURE_LAYERS = getInteger(GL_MAX_ARRAY_TEXTURE_LAYERS)

                if (check(4, 3) || extensions.ARB_texture_buffer_object)
                    TEXTURE_BUFFER_OFFSET_ALIGNMENT = getInteger(GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT)
            }
        }
    }

    inner class Formats(gl: GL) {

        val compressed by lazy {
            val buffer = intBufferBig(limits.NUM_COMPRESSED_TEXTURE_FORMATS)
            gl.glGetIntegerv(GL_COMPRESSED_TEXTURE_FORMATS, buffer)
            val formats = (0 until buffer.capacity()).map { buffer[it] }
            buffer.destroy()
            formats
        }

        val COMPRESSED_RGB_S3TC_DXT1_EXT = compressed.contains(GL_COMPRESSED_RGB_S3TC_DXT1_EXT)
        val COMPRESSED_RGBA_S3TC_DXT1_EXT = compressed.contains(GL_COMPRESSED_RGBA_S3TC_DXT1_EXT)
        val COMPRESSED_RGBA_S3TC_DXT3_EXT = compressed.contains(GL_COMPRESSED_RGBA_S3TC_DXT3_EXT)
        val COMPRESSED_RGBA_S3TC_DXT5_EXT = compressed.contains(GL_COMPRESSED_RGBA_S3TC_DXT5_EXT)
        val COMPRESSED_SRGB_S3TC_DXT1_EXT = compressed.contains(GL_COMPRESSED_SRGB_S3TC_DXT1_EXT)
        val COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT = compressed.contains(GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT)
        val COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT = compressed.contains(GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT)
        val COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT = compressed.contains(GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT)

        val COMPRESSED_RED_RGTC1 = compressed.contains(GL_COMPRESSED_RED_RGTC1)
        val COMPRESSED_SIGNED_RED_RGTC1 = compressed.contains(GL_COMPRESSED_SIGNED_RED_RGTC1)
        val COMPRESSED_RG_RGTC2 = compressed.contains(GL_COMPRESSED_RG_RGTC2)
        val COMPRESSED_SIGNED_RG_RGTC2 = compressed.contains(GL_COMPRESSED_SIGNED_RG_RGTC2)
        val COMPRESSED_RGBA_BPTC_UNORM = compressed.contains(GL_COMPRESSED_RGBA_BPTC_UNORM)
        val COMPRESSED_SRGB_ALPHA_BPTC_UNORM = compressed.contains(GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM)
        val COMPRESSED_RGB_BPTC_SIGNED_FLOAT = compressed.contains(GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT)
        val COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = compressed.contains(GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT)
        val COMPRESSED_R11_EAC = compressed.contains(GL_COMPRESSED_R11_EAC)
        val COMPRESSED_SIGNED_R11_EAC = compressed.contains(GL_COMPRESSED_SIGNED_R11_EAC)
        val COMPRESSED_RG11_EAC = compressed.contains(GL_COMPRESSED_RG11_EAC)
        val COMPRESSED_SIGNED_RG11_EAC = compressed.contains(GL_COMPRESSED_SIGNED_RG11_EAC)
        val COMPRESSED_RGB8_ETC2 = compressed.contains(GL_COMPRESSED_RGB8_ETC2)
        val COMPRESSED_SRGB8_ETC2 = compressed.contains(GL_COMPRESSED_SRGB8_ETC2)
        val COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = compressed.contains(GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2)
        val COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = compressed.contains(GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2)
        val COMPRESSED_RGBA8_ETC2_EAC = compressed.contains(GL_COMPRESSED_RGBA8_ETC2_EAC)
        val COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC)

        val COMPRESSED_RGBA_ASTC_4x4_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_4x4_KHR)
        val COMPRESSED_RGBA_ASTC_5x4_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_5x4_KHR)
        val COMPRESSED_RGBA_ASTC_5x5_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_5x5_KHR)
        val COMPRESSED_RGBA_ASTC_6x5_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_6x5_KHR)
        val COMPRESSED_RGBA_ASTC_6x6_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_6x6_KHR)
        val COMPRESSED_RGBA_ASTC_8x5_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_8x5_KHR)
        val COMPRESSED_RGBA_ASTC_8x6_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_8x6_KHR)
        val COMPRESSED_RGBA_ASTC_8x8_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_8x8_KHR)
        val COMPRESSED_RGBA_ASTC_10x5_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_10x5_KHR)
        val COMPRESSED_RGBA_ASTC_10x6_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_10x6_KHR)
        val COMPRESSED_RGBA_ASTC_10x8_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_10x8_KHR)
        val COMPRESSED_RGBA_ASTC_10x10_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_10x10_KHR)
        val COMPRESSED_RGBA_ASTC_12x10_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_12x10_KHR)
        val COMPRESSED_RGBA_ASTC_12x12_KHR = compressed.contains(GL_COMPRESSED_RGBA_ASTC_12x12_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_4x4_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_4x4_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_5x4_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_5x4_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_5x5_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_5x5_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_6x5_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_6x5_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_6x6_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_6x6_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_8x5_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_8x5_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_8x6_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_8x6_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_8x8_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_8x8_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_10x5_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_10x5_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_10x6_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_10x6_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_10x8_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_10x8_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_10x10_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_10x10_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_12x10_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_12x10_KHR)
        val COMPRESSED_SRGB8_ALPHA8_ASTC_12x12_KHR = compressed.contains(GL_COMPRESSED_SRGB8_ALPHA8_ASTC_12x12_KHR)

        val COMPRESSED_LUMINANCE_LATC1_EXT = compressed.contains(GL_COMPRESSED_LUMINANCE_LATC1_EXT)
        val COMPRESSED_SIGNED_LUMINANCE_LATC1_EXT = compressed.contains(GL_COMPRESSED_SIGNED_LUMINANCE_LATC1_EXT)
        val COMPRESSED_LUMINANCE_ALPHA_LATC2_EXT = compressed.contains(GL_COMPRESSED_LUMINANCE_ALPHA_LATC2_EXT)
        val COMPRESSED_SIGNED_LUMINANCE_ALPHA_LATC2_EXT = compressed.contains(GL_COMPRESSED_SIGNED_LUMINANCE_ALPHA_LATC2_EXT)
        val COMPRESSED_LUMINANCE_ALPHA_3DC_ATI = compressed.contains(GL_COMPRESSED_LUMINANCE_ALPHA_3DC_ATI)

        val COMPRESSED_RGB_FXT1_3DFX = compressed.contains(GL_COMPRESSED_RGB_FXT1_3DFX)
        val COMPRESSED_RGBA_FXT1_3DFX = compressed.contains(GL_COMPRESSED_RGBA_FXT1_3DFX)

        val PALETTE4_RGB8_OES = compressed.contains(GL_PALETTE4_RGB8_OES)
        val PALETTE4_RGBA8_OES = compressed.contains(GL_PALETTE4_RGBA8_OES)
        val PALETTE4_R5_G6_B5_OES = compressed.contains(GL_PALETTE4_R5_G6_B5_OES)
        val PALETTE4_RGBA4_OES = compressed.contains(GL_PALETTE4_RGBA4_OES)
        val PALETTE4_RGB5_A1_OES = compressed.contains(GL_PALETTE4_RGB5_A1_OES)
        val PALETTE8_RGB8_OES = compressed.contains(GL_PALETTE8_RGB8_OES)
        val PALETTE8_RGBA8_OES = compressed.contains(GL_PALETTE8_RGBA8_OES)
        val PALETTE8_R5_G6_B5_OES = compressed.contains(GL_PALETTE8_R5_G6_B5_OES)
        val PALETTE8_RGBA4_OES = compressed.contains(GL_PALETTE8_RGBA4_OES)
        val PALETTE8_RGB5_A1_OES = compressed.contains(GL_PALETTE8_RGB5_A1_OES)
        val ETC1_RGB8_OES = compressed.contains(GL_ETC1_RGB8_OES)
    }


    fun check(majorVersionRequire: Int, minorVersionRequire: Int) = version.check(majorVersionRequire, minorVersionRequire)

    enum class Profile(@JvmField val i: Int) {

        CORE(0x1),
        COMPATIBILITY(0x2),
        ES(0x4)
    }
}