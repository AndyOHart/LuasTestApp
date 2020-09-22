package testUtils

import com.example.luastestapp.model.Direction
import com.example.luastestapp.model.StopInfo
import com.example.luastestapp.model.Tram

object TestUtils {

    @JvmStatic
    fun getMockLuasStop(): StopInfo {
        val inboundTrams = ArrayList<Tram>()
        val outboundTrams = ArrayList<Tram>()
        inboundTrams.add(Tram("Broomsbridge", "1"))
        inboundTrams.add(Tram("Stillorgan", "11"))
        inboundTrams.add(Tram("Broomsbridge", "15"))
        inboundTrams.add(Tram("Stillorgan", "19"))
        outboundTrams.add(Tram("Marlborough", "1"))
        outboundTrams.add(Tram("Liffey Valley", "11"))
        outboundTrams.add(Tram("Marlborough", "15"))
        outboundTrams.add(Tram("Liffey Valley", "19"))
        val directions = ArrayList<Direction>()
        val inboundDirection = Direction("Inbound", inboundTrams)
        val outboundDirection = Direction("Outbound", outboundTrams)
        directions.add(inboundDirection)
        directions.add(outboundDirection)
        return StopInfo(
            "2020-09-21T01:34:33",
            "Stillorgan",
            "STIR",
            "Green Line services operating normally",
            directions
        )
    }
}