package lt.markmerkk

import net.bramp.ffmpeg.builder.FFmpegBuilder

/**
 * @author mariusmerkevicius
 * @since 2016-11-06
 */
class FFmpegBuilderConcat : FFmpegBuilder() {
    override fun build(): MutableList<String>? {
        val args = super.build().toMutableList()
        args.add(3, "concat")
        args.add(3, "-f")
        args.add(args.size-1, "-c")
        args.add(args.size-1, "copy")
        return args
    }
}