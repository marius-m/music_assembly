package lt.markmerkk

import net.bramp.ffmpeg.FFmpeg
import net.bramp.ffmpeg.FFmpegExecutor
import net.bramp.ffmpeg.FFprobe
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-11-05
 */
class MusicAssembler() {
    fun testMp3ToMp4() {
        val ffmpeg = FFmpeg()
        logger.info("Running ${ffmpeg.version()}")
        val ffprobe = FFprobe()
        val executor = FFmpegExecutor(ffmpeg, ffprobe)

        val builder = FFmpegBuilderMp3ToMp4()
                .addInput("image.jpg")
                .addInput("input.mp3")
                .addOutput("output.mp4")
                .done()
        val builder2 = FFmpegBuilderMp3ToMp4()
                .addInput("image2.jpg")
                .addInput("input.mp3")
                .addOutput("output2.mp4")
                .done()
        val builder3 = FFmpegBuilderConcatWithCrossfade()
                .addInput("output.mp4")
                .addInput("output2.mp4")
                .addOutput("result.mp4")
                .done()
        executor.createJob(builder).run()
        executor.createJob(builder2).run()
        executor.createJob(builder3).run()
    }

    fun testAssembly() {
        val ffmpeg = FFmpeg()
        logger.info("Running ${ffmpeg.version()}")
        val ffprobe = FFprobe()
        val builder = FFmpegBuilderConcat()
                .setInput("list.txt")
                .addOutput("output.mp3")
                .done()
        val executor = FFmpegExecutor(ffmpeg, ffprobe)
        executor.createJob(builder).run()
    }

    fun workingAssembly() {
        val ffmpeg = FFmpeg()
        logger.info("Running ${ffmpeg.version()}")
        val ffprobe = FFprobe()
        val builder = ffmpeg.builder()
                .setInput("input.mp3")
                .addOutput("output.mp3")
                .done()
        val executor = FFmpegExecutor(ffmpeg, ffprobe)
        executor.createJob(builder).run()
    }

    companion object {
        val logger = LoggerFactory.getLogger(MusicAssembler::class.java)
    }

}