package arsensaruhanyan.com.travelmet

import java.sql.Date
import java.sql.Time

data class Place(val id: Int, val title: String, val city: String, val price: String, val photoId: String, val type: Int, val locationStartPoint: String, val locationEndPoint: String, val timeAmount: String, val timeStart: List<Time>, val timeEnd: List<Time>, val unableDates: List<Date>, val lastDate: List<Date>, val bonus: String)