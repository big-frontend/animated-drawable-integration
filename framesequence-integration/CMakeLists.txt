cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_VERBOSE_MAKEFILE on)

#==============giflib
set(giflib_src_dir src/main/jni/giflib)
set(gifcodec_src_files
        ${giflib_src_dir}/dgif_lib.c ${giflib_src_dir}/egif_lib.c ${giflib_src_dir}/gif_err.c ${giflib_src_dir}/gif_font.c
        ${giflib_src_dir}/gif_hash.c ${giflib_src_dir}/gifalloc.c ${giflib_src_dir}/openbsd-reallocarray.c ${giflib_src_dir}/quantize.c)
add_library(giflib STATIC ${gifcodec_src_files})
target_link_libraries(giflib log)

#==============framesequence
set(frameseq_src_dir src/main/jni/framesequence)
set(frameseq_src_files
        ${frameseq_src_dir}/BitmapDecoderJNI.cpp
        ${frameseq_src_dir}/FrameSequence.cpp
        ${frameseq_src_dir}/FrameSequenceJNI.cpp
        ${frameseq_src_dir}/FrameSequence_gif.cpp
        ${frameseq_src_dir}/JNIHelpers.cpp
        ${frameseq_src_dir}/Registry.cpp
        ${frameseq_src_dir}/Stream.cpp
        )
add_library(framesequence SHARED ${frameseq_src_files})
#ifeq ($(FRAMESEQUENCE_INCLUDE_WEBP),true)
#	LOCAL_C_INCLUDES += external/webp/include
#	LOCAL_SRC_FILES += FrameSequence_webp.cpp
#	LOCAL_STATIC_LIBRARIES += libwebp-decode
#endif
target_include_directories(framesequence PRIVATE
        ${ANDROID_NDK}/sources/android/native_app_glue ${giflib_src_dir}
        )

target_link_libraries(
        framesequence
        giflib android z EGL jnigraphics mediandk
        OpenMAXAL
        #        vulkan
        #        sync
        #        camera2ndk
        #        nativewindow
        z m log
        )