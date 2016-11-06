package lt.markmerkk

import net.bramp.ffmpeg.builder.FFmpegBuilder

/**
 * @author mariusmerkevicius
 * @since 2016-11-06
 */
class FFmpegBuilderConcatWithCrossfade : FFmpegBuilder() {
    override fun build(): MutableList<String>? {
        val args = super.build().toMutableList()
        args.add(args.size - 1, "-an")
        args.add(args.size - 1, "-filter_complex")
        args.add(args.size - 1,
                "[0:v]trim=start=0:end=9,setpts=PTS-STARTPTS[firstclip];" +
                "[1:v]trim=start=1,setpts=PTS-STARTPTS[secondclip];" +
                "[0:v]trim=start=9:end=10,setpts=PTS-STARTPTS[fadeoutsrc]; " +
                "[1:v]trim=start=0:end=1,setpts=PTS-STARTPTS[fadeinsrc];" +
                "[fadeinsrc]format=pix_fmts=yuva420p," +
                "fade=t=in:st=0:d=1:alpha=1[fadein];" +
                "[fadeoutsrc]format=pix_fmts=yuva420p," +
                "fade=t=out:st=0:d=1:alpha=1[fadeout];" +
                "[fadein]fifo[fadeinfifo];" +
                "[fadeout]fifo[fadeoutfifo];" +
                "[fadeoutfifo][fadeinfifo]overlay[crossfade];" +
                "[firstclip][crossfade][secondclip]concat=n=3[output];" +
                "[0:a][1:a] acrossfade=d=1 [audio]"
        )
        args.add(args.size - 1, "-map")
        args.add(args.size - 1, "[output]")
        args.add(args.size - 1, "-map")
        args.add(args.size - 1, "[audio]")
        return args
    }
}