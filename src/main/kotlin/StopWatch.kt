import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class StopWatch {

    var formattedTime by mutableStateOf("00:00:000")

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    var isActive=false

    var timeMillis=0L
    var lastTimeStamp=0L

    fun start(){
        if (isActive) return

        coroutineScope.launch {
            lastTimeStamp=System.currentTimeMillis()
            this@StopWatch.isActive=true
            while (isActive){
                delay(10L)
                //simple timer logic
                timeMillis+=System.currentTimeMillis()-lastTimeStamp
                lastTimeStamp=System.currentTimeMillis()
                formattedTime=formatTime(timeMillis)
            }
        }
    }
    fun pause(){
        isActive=false
    }
    fun reset(){
        coroutineScope.cancel()
        coroutineScope= CoroutineScope(Dispatchers.Main)
        timeMillis=0L
        formattedTime="00:00:000"
        isActive=false
    }

    private fun formatTime(timeMillis:Long):String{
        val localDateTime=LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMillis),
            ZoneId.systemDefault()
        )
        val formatter=DateTimeFormatter.ofPattern("mm:ss:SSS",
            Locale.getDefault())

        return localDateTime.format(formatter)

    }
}