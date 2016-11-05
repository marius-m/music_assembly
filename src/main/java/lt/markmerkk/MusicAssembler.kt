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