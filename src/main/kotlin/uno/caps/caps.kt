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

    val version = Version(gl, profile)
    val extensions = Extensions(gl)
    val debug = Debug(gl)
    val limits = Limits(gl)

    inner class Version(gl: GL, val profile: Profile) {

        val MINOR_VERSION = gl.getInteger(GL_MINOR_VERSION)
        val MAJOR_VERSION = gl.getInteger(GL_MAJOR_VERSION)
        val CONTEXT_FLAGS =
                if (check(4, 3) || gl.isExtensionAvailable("GL_KHR_debug"))
                    gl.getInteger(GL_CONTEXT_FLAGS)
                else 0
        val NUM_EXTENSIONS = gl.getInteger(GL_NUM_EXTENSIONS)
        val RENDERER = gl.getString(GL_RENDERER)
        val VENDOR = gl.getString(GL_VENDOR)
        val VERSION = gl.getString(GL_VERSION)
        val SHADING_LANGUAGE_VERSION = gl.getString(GL_SHADING_LANGUAGE_VERSION)
        val NUM_SHADING_LANGUAGE_VERSIONS =
                if (check(4, 3))
                    gl.getInteger(GL_NUM_SHADING_LANGUAGE_VERSIONS)
                else 0


        private val glslVersions by lazy {
            (0 until NUM_SHADING_LANGUAGE_VERSIONS).map { (gl as GL2).getStringi(GL_SHADING_LANGUAGE_VERSION, it) }
        }

        val glsl100 = glslVersions.contains("100")
        val glsl110 = glslVersions.contains("110")
        val glsl120 = glslVersions.contains("120")
        val glsl130 = glslVersions.contains("130")
        val glsl140 = glslVersions.contains("140")
        val glsl150Core = glslVersions.contains("150 core")
        val glsl150Comp = glslVersions.contains("150 compatibility")
        val glsl300ES = glslVersions.contains("300 es")
        val glsl330Core = glslVersions.contains("330 core")
        val glsl330Comp = glslVersions.contains("330 compatibility")
        val glsl400Core = glslVersions.contains("400 core")
        val glsl400Comp = glslVersions.contains("400 compatibility")
        val glsl410Core = glslVersions.contains("410 core")
        val glsl410Comp = glslVersions.contains("410 compatibility")
        val glsl420Core = glslVersions.contains("420 core")
        val glsl420Comp = glslVersions.contains("420 compatibility")
        val glsl430Core = glslVersions.contains("430 core")
        val glsl430Comp = glslVersions.contains("430 compatibility")
        val glsl440Core = glslVersions.contains("440 core")
        val glsl440Comp = glslVersions.contains("440 compatibility")
        val glsl450Core = glslVersions.contains("450 core")
        val glsl450Comp = glslVersions.contains("450 cocompatibilityre")
    }

    inner class Extensions(gl: GL) {

        private val exts: List<String> by lazy { gl.glGetString(GL_EXTENSIONS).split("\\s+".toRegex()) }

        val ARB_multitexture = exts.contains("GL_ARB_multitexture")
        val ARB_transpose_matrix = exts.contains("GL_ARB_transpose_matrix")
        val ARB_multisample = exts.contains("GL_ARB_multisample")
        val ARB_texture_env_add = exts.contains("GL_ARB_texture_env_add")
        val ARB_texture_cube_map = exts.contains("GL_ARB_texture_cube_map")
        val ARB_texture_compression = exts.contains("GL_ARB_texture_compression")
        val ARB_texture_border_clamp = exts.contains("GL_ARB_texture_border_clamp")
        val ARB_point_parameters = exts.contains("GL_ARB_point_parameters")
        val ARB_vertex_blend = exts.contains("GL_ARB_vertex_blend")
        val ARB_matrix_palette = exts.contains("GL_ARB_matrix_palette")
        val ARB_texture_env_combine = exts.contains("GL_ARB_texture_env_combine")
        val ARB_texture_env_crossbar = exts.contains("GL_ARB_texture_env_crossbar")
        val ARB_texture_env_dot3 = exts.contains("GL_ARB_texture_env_dot3")
        val ARB_texture_mirrored_repeat = exts.contains("GL_ARB_texture_mirrored_repeat")
        val ARB_depth_texture = exts.contains("GL_ARB_depth_texture")
        val ARB_shadow = exts.contains("GL_ARB_shadow")
        val ARB_shadow_ambient = exts.contains("GL_ARB_shadow_ambient")
        val ARB_window_pos = exts.contains("GL_ARB_window_pos")
        val ARB_vertex_program = exts.contains("GL_ARB_vertex_program")
        val ARB_fragment_program = exts.contains("GL_ARB_fragment_program")
        val ARB_vertex_buffer_object = exts.contains("GL_ARB_vertex_buffer_object")
        val ARB_occlusion_query = exts.contains("GL_ARB_occlusion_query")
        val ARB_shader_objects = exts.contains("GL_ARB_shader_objects")
        val ARB_vertex_shader = exts.contains("GL_ARB_vertex_shader")
        val ARB_fragment_shader = exts.contains("GL_ARB_fragment_shader")
        val ARB_shading_language_100 = exts.contains("GL_ARB_shading_language_100")
        val ARB_texture_non_power_of_two = exts.contains("GL_ARB_texture_non_power_of_two")
        val ARB_point_sprite = exts.contains("GL_ARB_point_sprite")
        val ARB_fragment_program_shadow = exts.contains("GL_ARB_fragment_program_shadow")
        val ARB_draw_buffers = exts.contains("GL_ARB_draw_buffers")
        val ARB_texture_rectangle = exts.contains("GL_ARB_texture_rectangle")
        val ARB_color_buffer_float = exts.contains("GL_ARB_color_buffer_float")
        val ARB_half_float_pixel = exts.contains("GL_ARB_half_float_pixel")
        val ARB_texture_float = exts.contains("GL_ARB_texture_float")
        val ARB_pixel_buffer_object = exts.contains("GL_ARB_pixel_buffer_object")
        val ARB_depth_buffer_float = exts.contains("GL_ARB_depth_buffer_float")
        val ARB_draw_instanced = exts.contains("GL_ARB_draw_instanced")
        val ARB_framebuffer_object = exts.contains("GL_ARB_framebuffer_object")
        val ARB_framebuffer_sRGB = exts.contains("GL_ARB_framebuffer_sRGB")
        val ARB_geometry_shader4 = exts.contains("GL_ARB_geometry_shader4")
        val ARB_half_float_vertex = exts.contains("GL_ARB_half_float_vertex")
        val ARB_instanced_arrays = exts.contains("GL_ARB_instanced_arrays")
        val ARB_map_buffer_range = exts.contains("GL_ARB_map_buffer_range")
        val ARB_texture_buffer_object = exts.contains("GL_ARB_texture_buffer_object")
        val ARB_texture_compression_rgtc = exts.contains("GL_ARB_texture_compression_rgtc")
        val ARB_texture_rg = exts.contains("GL_ARB_texture_rg")
        val ARB_vertex_array_object = exts.contains("GL_ARB_vertex_array_object")
        val ARB_uniform_buffer_object = exts.contains("GL_ARB_uniform_buffer_object")
        val ARB_compatibility = exts.contains("GL_ARB_compatibility")
        val ARB_copy_buffer = exts.contains("GL_ARB_copy_buffer")
        val ARB_shader_texture_lod = exts.contains("GL_ARB_shader_texture_lod")
        val ARB_depth_clamp = exts.contains("GL_ARB_depth_clamp")
        val ARB_draw_elements_base_vertex = exts.contains("GL_ARB_draw_elements_base_vertex")
        val ARB_fragment_coord_conventions = exts.contains("GL_ARB_fragment_coord_conventions")
        val ARB_provoking_vertex = exts.contains("GL_ARB_provoking_vertex")
        val ARB_seamless_cube_map = exts.contains("GL_ARB_seamless_cube_map")
        val ARB_sync = exts.contains("GL_ARB_sync")
        val ARB_texture_multisample = exts.contains("GL_ARB_texture_multisample")
        val ARB_vertex_array_bgra = exts.contains("GL_ARB_vertex_array_bgra")
        val ARB_draw_buffers_blend = exts.contains("GL_ARB_draw_buffers_blend")
        val ARB_sample_shading = exts.contains("GL_ARB_sample_shading")
        val ARB_texture_cube_map_array = exts.contains("GL_ARB_texture_cube_map_array")
        val ARB_texture_gather = exts.contains("GL_ARB_texture_gather")
        val ARB_texture_query_lod = exts.contains("GL_ARB_texture_query_lod")
        val ARB_shading_language_include = exts.contains("GL_ARB_shading_language_include")
        val ARB_texture_compression_bptc = exts.contains("GL_ARB_texture_compression_bptc")
        val ARB_blend_func_extended = exts.contains("GL_ARB_blend_func_extended")
        val ARB_explicit_attrib_location = exts.contains("GL_ARB_explicit_attrib_location")
        val ARB_occlusion_query2 = exts.contains("GL_ARB_occlusion_query2")
        val ARB_sampler_objects = exts.contains("GL_ARB_sampler_objects")
        val ARB_shader_bit_encoding = exts.contains("GL_ARB_shader_bit_encoding")
        val ARB_texture_rgb10_a2ui = exts.contains("GL_ARB_texture_rgb10_a2ui")
        val ARB_texture_swizzle = exts.contains("GL_ARB_texture_swizzle")
        val ARB_timer_query = exts.contains("GL_ARB_timer_query")
        val ARB_vertex_type_2_10_10_10_rev = exts.contains("GL_ARB_vertex_type_2_10_10_10_rev")
        val ARB_draw_indirect = exts.contains("GL_ARB_draw_indirect")
        val ARB_gpu_shader5 = exts.contains("GL_ARB_gpu_shader5")
        val ARB_gpu_shader_fp64 = exts.contains("GL_ARB_gpu_shader_fp64")
        val ARB_shader_subroutine = exts.contains("GL_ARB_shader_subroutine")
        val ARB_tessellation_shader = exts.contains("GL_ARB_tessellation_shader")
        val ARB_texture_buffer_object_rgb32 = exts.contains("GL_ARB_texture_buffer_object_rgb32")
        val ARB_transform_feedback2 = exts.contains("GL_ARB_transform_feedback2")
        val ARB_transform_feedback3 = exts.contains("GL_ARB_transform_feedback3")
        val ARB_ES2_compatibility = exts.contains("GL_ARB_ES2_compatibility")
        val ARB_get_program_binary = exts.contains("GL_ARB_get_program_binary")
        val ARB_separate_shader_objects = exts.contains("GL_ARB_separate_shader_objects")
        val ARB_shader_precision = exts.contains("GL_ARB_shader_precision")
        val ARB_vertex_attrib_64bit = exts.contains("GL_ARB_vertex_attrib_64bit")
        val ARB_viewport_array = exts.contains("GL_ARB_viewport_array")
        val ARB_cl_event = exts.contains("GL_ARB_cl_event")
        val ARB_debug_output = exts.contains("GL_ARB_debug_output")
        val ARB_robustness = exts.contains("GL_ARB_robustness")
        val ARB_shader_stencil_export = exts.contains("GL_ARB_shader_stencil_export")
        val ARB_base_instance = exts.contains("GL_ARB_base_instance")
        val ARB_shading_language_420pack = exts.contains("GL_ARB_shading_language_420pack")
        val ARB_transform_feedback_instanced = exts.contains("GL_ARB_transform_feedback_instanced")
        val ARB_compressed_texture_pixel_storage = exts.contains("GL_ARB_compressed_texture_pixel_storage")
        val ARB_conservative_depth = exts.contains("GL_ARB_conservative_depth")
        val ARB_internalformat_query = exts.contains("GL_ARB_internalformat_query")
        val ARB_map_buffer_alignment = exts.contains("GL_ARB_map_buffer_alignment")
        val ARB_shader_atomic_counters = exts.contains("GL_ARB_shader_atomic_counters")
        val ARB_shader_image_load_store = exts.contains("GL_ARB_shader_image_load_store")
        val ARB_shading_language_packing = exts.contains("GL_ARB_shading_language_packing")
        val ARB_texture_storage = exts.contains("GL_ARB_texture_storage")
        val KHR_texture_compression_astc_hdr = exts.contains("GL_KHR_texture_compression_astc_hdr")
        val KHR_texture_compression_astc_ldr = exts.contains("GL_KHR_texture_compression_astc_ldr")
        val KHR_debug = exts.contains("GL_KHR_debug")
        val ARB_arrays_of_arrays = exts.contains("GL_ARB_arrays_of_arrays")
        val ARB_clear_buffer_object = exts.contains("GL_ARB_clear_buffer_object")
        val ARB_compute_shader = exts.contains("GL_ARB_compute_shader")
        val ARB_copy_image = exts.contains("GL_ARB_copy_image")
        val ARB_texture_view = exts.contains("GL_ARB_texture_view")
        val ARB_vertex_attrib_binding = exts.contains("GL_ARB_vertex_attrib_binding")
        val ARB_robustness_isolation = exts.contains("GL_ARB_robustness_isolation")
        val ARB_ES3_compatibility = exts.contains("GL_ARB_ES3_compatibility")
        val ARB_explicit_uniform_location = exts.contains("GL_ARB_explicit_uniform_location")
        val ARB_fragment_layer_viewport = exts.contains("GL_ARB_fragment_layer_viewport")
        val ARB_framebuffer_no_attachments = exts.contains("GL_ARB_framebuffer_no_attachments")
        val ARB_internalformat_query2 = exts.contains("GL_ARB_internalformat_query2")
        val ARB_invalidate_subdata = exts.contains("GL_ARB_invalidate_subdata")
        val ARB_multi_draw_indirect = exts.contains("GL_ARB_multi_draw_indirect")
        val ARB_program_interface_query = exts.contains("GL_ARB_program_interface_query")
        val ARB_robust_buffer_access_behavior = exts.contains("GL_ARB_robust_buffer_access_behavior")
        val ARB_shader_image_size = exts.contains("GL_ARB_shader_image_size")
        val ARB_shader_storage_buffer_object = exts.contains("GL_ARB_shader_storage_buffer_object")
        val ARB_stencil_texturing = exts.contains("GL_ARB_stencil_texturing")
        val ARB_texture_buffer_range = exts.contains("GL_ARB_texture_buffer_range")
        val ARB_texture_query_levels = exts.contains("GL_ARB_texture_query_levels")
        val ARB_texture_storage_multisample = exts.contains("GL_ARB_texture_storage_multisample")
        val ARB_buffer_storage = exts.contains("GL_ARB_buffer_storage")
        val ARB_clear_texture = exts.contains("GL_ARB_clear_texture")
        val ARB_enhanced_layouts = exts.contains("GL_ARB_enhanced_layouts")
        val ARB_multi_bind = exts.contains("GL_ARB_multi_bind")
        val ARB_query_buffer_object = exts.contains("GL_ARB_query_buffer_object")
        val ARB_texture_mirror_clamp_to_edge = exts.contains("GL_ARB_texture_mirror_clamp_to_edge")
        val ARB_texture_stencil8 = exts.contains("GL_ARB_texture_stencil8")
        val ARB_vertex_type_10f_11f_11f_rev = exts.contains("GL_ARB_vertex_type_10f_11f_11f_rev")
        val ARB_bindless_texture = exts.contains("GL_ARB_bindless_texture")
        val ARB_compute_valiable_group_size = exts.contains("GL_ARB_compute_valiable_group_size")
        val ARB_indirect_parameters = exts.contains("GL_ARB_indirect_parameters")
        val ARB_seamless_cubemap_per_texture = exts.contains("GL_ARB_seamless_cubemap_per_texture")
        val ARB_shader_draw_parameters = exts.contains("GL_ARB_shader_draw_parameters")
        val ARB_shader_group_vote = exts.contains("GL_ARB_shader_group_vote")
        val ARB_sparse_texture = exts.contains("GL_ARB_sparse_texture")
        val ARB_ES3_1_compatibility = exts.contains("GL_ARB_ES3_1_compatibility")
        val ARB_clip_control = exts.contains("GL_ARB_clip_control")
        val ARB_conditional_render_inverted = exts.contains("GL_ARB_conditional_render")
        val ARB_cull_distance = exts.contains("GL_ARB_cull_distance")
        val ARB_derivative_control = exts.contains("GL_ARB_derivative_control")
        val ARB_direct_state_access = exts.contains("GL_ARB_direct_state_access")
        val ARB_get_texture_sub_image = exts.contains("GL_ARB_get_texture_sub_image")
        val ARB_shader_texture_image_samples = exts.contains("GL_ARB_shader_texture_image_samples")
        val ARB_texture_barrier = exts.contains("GL_ARB_texture_barrier")
        val KHR_context_flush_control = exts.contains("GL_KHR_context_flush_control")
        val KHR_robust_buffer_access_behavior = exts.contains("GL_KHR_robust_buffer_access_behavior")
        val KHR_robustness = exts.contains("GL_KHR_robustness")
        val ARB_pipeline_statistics_query = exts.contains("GL_ARB_pipeline_statistics_query")
        val ARB_sparse_buffer = exts.contains("GL_ARB_sparse_buffer")
        val ARB_transform_feedback_overflow_query = exts.contains("GL_ARB_transform_feedback_overflow_query")

        // EXT
        val EXT_texture_compression_latc = exts.contains("GL_EXT_texture_compression_latc")
        val EXT_transform_feedback = exts.contains("GL_EXT_transform_feedback")
        val EXT_direct_state_access = exts.contains("GL_EXT_direct_state_access")
        val EXT_texture_filter_anisotropic = exts.contains("GL_EXT_texture_filter_anisotropic")
        val EXT_texture_compression_s3tc = exts.contains("GL_EXT_texture_compression_s3tc")
        val EXT_texture_array = exts.contains("GL_EXT_texture_array")
        val EXT_texture_snorm = exts.contains("GL_EXT_texture_snorm")
        val EXT_texture_sRGB_decode = exts.contains("GL_EXT_texture_sRGB_decode")
        val EXT_framebuffer_multisample_blit_scaled = exts.contains("GL_EXT_framebuffer_multisample_blit_scaled")
        val EXT_shader_integer_mix = exts.contains("GL_EXT_shader_integer_mix")
        val EXT_shader_image_load_formatted = exts.contains("GL_EXT_shader_image_load_formatted")
        val EXT_polygon_offset_clamp = exts.contains("GL_EXT_polygon_offset_clamp")

        // NV
        val NV_explicit_multisample = exts.contains("GL_NV_explicit_multisample")
        val NV_shader_buffer_load = exts.contains("GL_NV_shader_buffer_load")
        val NV_vertex_buffer_unified_memory = exts.contains("GL_NV_vertex_buffer_unified_memory")
        val NV_shader_buffer_store = exts.contains("GL_NV_shader_buffer_store")
        val NV_bindless_multi_draw_indirect = exts.contains("GL_NV_bindless_multi_draw_indirect")
        val NV_blend_equation_advanced = exts.contains("GL_NV_blend_equation_advanced")
        val NV_deep_texture3D = exts.contains("GL_NV_deep_texture3D")
        val NV_shader_thread_group = exts.contains("GL_NV_shader_thread_group")
        val NV_shader_thread_shuffle = exts.contains("GL_NV_shader_thread_shuffle")
        val NV_shader_atomic_int64 = exts.contains("GL_NV_shader_atomic_int64")
        val NV_bindless_multi_draw_indirect_count = exts.contains("GL_NV_bindless_multi_draw_indirect_count")
        val NV_uniform_buffer_unified_memory = exts.contains("GL_NV_uniform_buffer_unified_memory")

        // AMD
        val ATI_texture_compression_3dc = exts.contains("GL_ATI_texture_compression_3dc")
        val AMD_depth_clamp_separate = exts.contains("GL_AMD_depth_clamp_separate")
        val AMD_stencil_operation_extended = exts.contains("GL_AMD_stencil_operation_extended")
        val AMD_vertex_shader_viewport_index = exts.contains("GL_AMD_vertex_shader_viewport_index")
        val AMD_vertex_shader_layer = exts.contains("GL_AMD_vertex_shader_layer")
        val AMD_shader_trinary_minmax = exts.contains("GL_AMD_shader_trinary_minmax")
        val AMD_interleaved_elements = exts.contains("GL_AMD_interleaved_elements")
        val AMD_shader_atomic_counter_ops = exts.contains("GL_AMD_shader_atomic_counter_ops")
        val AMD_occlusion_query_event = exts.contains("GL_AMD_occlusion_query_event")
        val AMD_shader_stencil_value_export = exts.contains("GL_AMD_shader_stencil_value_export")
        val AMD_transform_feedback4 = exts.contains("GL_AMD_transform_feedback4")
        val AMD_gpu_shader_int64 = exts.contains("GL_AMD_gpu_shader_int64")
        val AMD_gcn_shader = exts.contains("GL_AMD_gcn_shader")

        // Intel
        val INTEL_map_texture = exts.contains("GL_INTEL_map_texture")
        val INTEL_fragment_shader_ordering = exts.contains("GL_INTEL_fragment_shader_ordering")
        val INTEL_performance_query = exts.contains("GL_INTEL_performance_query")
    }

    inner class Debug(gl: GL) {
        val CONTEXT_FLAGS = gl.getInteger(GL_CONTEXT_FLAGS)
        val MAX_DEBUG_GROUP_STACK_DEPTH = gl.getInteger(GL_MAX_DEBUG_GROUP_STACK_DEPTH)
        val MAX_LABEL_LENGTH = gl.getInteger(GL_MAX_LABEL_LENGTH)
        val MAX_SERVER_WAIT_TIMEOUT = gl.getInteger(GL_MAX_SERVER_WAIT_TIMEOUT)
    }

    inner class Limits(gl: GL) {

        var MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 0
        var MAX_COMPUTE_UNIFORM_BLOCKS = 0
        var MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 0
        var MAX_COMPUTE_IMAGE_UNIFORMS = 0
        var MAX_COMPUTE_UNIFORM_COMPONENTS = 0
        var MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0
        var MAX_COMPUTE_ATOMIC_COUNTERS = 0
        var MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 0
        var MAX_COMPUTE_SHARED_MEMORY_SIZE = 0
        var MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 0
        var MAX_COMPUTE_WORK_GROUP_COUNT = 0
        var MAX_COMPUTE_WORK_GROUP_SIZE = 0

        var MAX_VERTEX_ATOMIC_COUNTERS = 0
        var MAX_VERTEX_SHADER_STORAGE_BLOCKS = 0
        var MAX_VERTEX_ATTRIBS = 0
        var MAX_VERTEX_OUTPUT_COMPONENTS = 0
        var MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0
        var MAX_VERTEX_UNIFORM_COMPONENTS = 0
        var MAX_VERTEX_UNIFORM_VECTORS = 0
        var MAX_VERTEX_UNIFORM_BLOCKS = 0
        var MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0

        var MAX_TESS_CONTROL_ATOMIC_COUNTERS = 0
        var MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 0
        var MAX_TESS_CONTROL_INPUT_COMPONENTS = 0
        var MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 0
        var MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 0
        var MAX_TESS_CONTROL_UNIFORM_BLOCKS = 0
        var MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 0
        var MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 0

        var MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 0
        var MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 0
        var MAX_TESS_EVALUATION_INPUT_COMPONENTS = 0
        var MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 0
        var MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 0
        var MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 0
        var MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 0
        var MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 0

        var MAX_GEOMETRY_ATOMIC_COUNTERS = 0
        var MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 0
        var MAX_GEOMETRY_INPUT_COMPONENTS = 0
        var MAX_GEOMETRY_OUTPUT_COMPONENTS = 0
        var MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 0
        var MAX_GEOMETRY_UNIFORM_BLOCKS = 0
        var MAX_GEOMETRY_UNIFORM_COMPONENTS = 0
        var MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 0
        var MAX_VERTEX_STREAMS = 0

        var MAX_FRAGMENT_ATOMIC_COUNTERS = 0
        var MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 0
        var MAX_FRAGMENT_INPUT_COMPONENTS = 0
        var MAX_FRAGMENT_UNIFORM_COMPONENTS = 0
        var MAX_FRAGMENT_UNIFORM_VECTORS = 0
        var MAX_FRAGMENT_UNIFORM_BLOCKS = 0
        var MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0
        var MAX_DRAW_BUFFERS = 0
        var MAX_DUAL_SOURCE_DRAW_BUFFERS = 0

        var MAX_COLOR_ATTACHMENTS = 0
        var MAX_FRAMEBUFFER_WIDTH = 0
        var MAX_FRAMEBUFFER_HEIGHT = 0
        var MAX_FRAMEBUFFER_LAYERS = 0
        var MAX_FRAMEBUFFER_SAMPLES = 0
        var MAX_SAMPLE_MASK_WORDS = 0

        var MAX_TRANSFORM_FEEDBACK_BUFFERS = 0
        var MIN_MAP_BUFFER_ALIGNMENT = 0

        var MAX_TEXTURE_IMAGE_UNITS = 0
        var MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0
        var MAX_RECTANGLE_TEXTURE_SIZE = 0
        var MAX_DEEP_3D_TEXTURE_WIDTH_HEIGHT_NV = 0
        var MAX_DEEP_3D_TEXTURE_DEPTH_NV = 0
        var MAX_COLOR_TEXTURE_SAMPLES = 0
        var MAX_DEPTH_TEXTURE_SAMPLES = 0
        var MAX_INTEGER_SAMPLES = 0
        var MAX_TEXTURE_BUFFER_SIZE = 0
        val NUM_COMPRESSED_TEXTURE_FORMATS = gl.getInteger(GL_NUM_COMPRESSED_TEXTURE_FORMATS)
        var MAX_TEXTURE_MAX_ANISOTROPY_EXT = 0

        var MAX_PATCH_VERTICES = 0
        var MAX_TESS_GEN_LEVEL = 0
        var MAX_SUBROUTINES = 0
        var MAX_SUBROUTINE_UNIFORM_LOCATIONS = 0
        var MAX_COMBINED_ATOMIC_COUNTERS = 0
        var MAX_COMBINED_SHADER_STORAGE_BLOCKS = 0
        var MAX_PROGRAM_TEXEL_OFFSET = 0
        var MIN_PROGRAM_TEXEL_OFFSET = 0
        var MAX_COMBINED_UNIFORM_BLOCKS = 0
        var MAX_UNIFORM_BUFFER_BINDINGS = 0
        var MAX_UNIFORM_BLOCK_SIZE = 0
        var MAX_UNIFORM_LOCATIONS = 0
        var MAX_VARYING_COMPONENTS = 0
        var MAX_VARYING_VECTORS = 0
        var MAX_VARYING_FLOATS = 0
        var MAX_SHADER_STORAGE_BUFFER_BINDINGS = 0
        var MAX_SHADER_STORAGE_BLOCK_SIZE = 0
        var MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0
        var SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 0
        var UNIFORM_BUFFER_OFFSET_ALIGNMENT = 0
        var NUM_PROGRAM_BINARY_FORMATS = 0
        var PROGRAM_BINARY_FORMATS = 0
        var NUM_SHADER_BINARY_FORMATS = 0
        var SHADER_BINARY_FORMATS: Nothing = TODO()

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

        var SUBPIXEL_BITS = 0
        var MAX_CLIP_DISTANCES = 0
        var MAX_CULL_DISTANCES = 0
        var MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 0
        var MAX_ELEMENT_INDEX = 0L
        var MAX_ELEMENTS_INDICES = 0
        var MAX_ELEMENTS_VERTICES = 0
        var IMPLEMENTATION_COLOR_READ_FORMAT = 0
        var IMPLEMENTATION_COLOR_READ_TYPE = 0
        val PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = gl.getBoolean(GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED)

        var MAX_3D_TEXTURE_SIZE = 0
        var MAX_TEXTURE_SIZE = 0
        var MAX_ARRAY_TEXTURE_LAYERS = 0
        var MAX_CUBE_MAP_TEXTURE_SIZE = 0
        var MAX_TEXTURE_LOD_BIAS = 0
        val MAX_RENDERBUFFER_SIZE = gl.getInteger(GL_MAX_RENDERBUFFER_SIZE)

        var MAX_VIEWPORT_DIMS = 0f
        var MAX_VIEWPORTS = 0
        var VIEWPORT_SUBPIXEL_BITS = 0
        var VIEWPORT_BOUNDS_RANGE = Vec2()

        var LAYER_PROVOKING_VERTEX = 0
        var VIEWPORT_INDEX_PROVOKING_VERTEX = 0

        var POINT_SIZE_MAX = 0f
        var POINT_SIZE_MIN = 0f
        val POINT_SIZE_RANGE = gl.getVec2(GL_POINT_SIZE_RANGE)
        val POINT_SIZE_GRANULARITY = gl.getFloat(GL_POINT_SIZE_GRANULARITY)

        val ALIASED_LINE_WIDTH_RANGE = gl.getVec2(GL_ALIASED_LINE_WIDTH_RANGE)
        val SMOOTH_LINE_WIDTH_RANGE = gl.getVec2(GL_SMOOTH_LINE_WIDTH_RANGE)
        val SMOOTH_LINE_WIDTH_GRANULARITY = gl.getFloat(GL_SMOOTH_LINE_WIDTH_GRANULARITY)

        var MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0
        var MAX_VERTEX_ATTRIB_BINDINGS = 0

        var TEXTURE_BUFFER_OFFSET_ALIGNMENT = 0

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


    fun check(majorVersionRequire: Int, minorVersionRequire: Int) =
            version.MAJOR_VERSION * 100 + version.MINOR_VERSION * 10 >= (majorVersionRequire * 100 + minorVersionRequire * 10)

    enum class Profile(@JvmField val i: Int) {

        CORE(0x1),
        COMPATIBILITY(0x2),
        ES(0x4)
    }
}