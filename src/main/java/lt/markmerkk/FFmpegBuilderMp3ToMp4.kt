package lt.markmerkk

import net.bramp.ffmpeg.builder.FFmpegBuilder

/**
 * @author mariusmerkevicius
 * @since 2016-11-06
 */
class FFmpegBuilderMp3ToMp4 : FFmpegBuilder() {
    override fun build(): MutableList<String>? {
        val args = super.build().toMutableList()
        args.add(1, "1")
        args.add(1, "-loop")
        args.add(args.size - 1, "-c:a")
        args.add(args.size - 1, "aac")
        args.add(args.size - 1, "-ab")
        args.add(args.size - 1, "112k")
        args.add(args.size - 1, "-c:v")
        args.add(args.size - 1, "libx264")
        args.add(args.size - 1, "-shortest")
        args.add(args.size - 1, "-strict")
        args.add(args.size - 1, "-2")
        return args
    }
}