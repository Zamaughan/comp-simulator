package d5700

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


class Timer(private val registers: Registers) {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var future: ScheduledFuture<*>? = null

    fun start() {
        future = executor.scheduleAtFixedRate({
            if (registers.t > 0) {
                registers.t -= 1
            }
        }, 0, 16, TimeUnit.MILLISECONDS)
    }

    fun stop() {
        future?.cancel(true)
        executor.shutdown()
    }
}
