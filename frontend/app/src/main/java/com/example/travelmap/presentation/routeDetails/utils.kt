package com.example.travelmap.presentation.routeDetails

import DayPlan
import Place
import androidx.compose.ui.graphics.Color

val pastelColors = listOf(
    Color(0xFFFDDDD2),
    Color(0xFFE3ECFD),
    Color(0xFFDFDBFC),
    Color(0xFFF4DAFF),
    Color(0xFFFFD7CA),
)


val plans = listOf(
    DayPlan(
        "09 July", listOf(
            Place(
                "09:00",
                "Duomo di Milano",
                "Marvel at the gothic cathedral and climb the rooftop terraces for a full city panorama."
            ),
            Place(
                "11:30",
                "Galleria Vittorio Emanuele II",
                "Luxury 19th-century shopping arcade beneath a glorious glass dome."
            ),
            Place(
                "14:30",
                "Brera District",
                "Lunch and an artsy stroll through bohemian lanes packed with galleries and cafés."
            ),
            Place(
                "17:00",
                "Castello Sforzesco",
                "15th-century fortress housing multiple museums — Michelangelo’s last Pietà included."
            )
        )
    ),

    DayPlan(
        "10 July", listOf(
            Place(
                "09:00",
                "Santa Maria delle Grazie",
                "UNESCO church famous for Leonardo’s *The Last Supper* (reserve tickets!)."
            ),
            Place(
                "11:00",
                "Parco Sempione",
                "Lakes, lawns and shady paths — perfect mid-morning reset."
            ),
            Place(
                "13:30",
                "Panzerotti Luini",
                "Grab a classic fried panzerotto for a quick Milanese snack."
            ),
            Place(
                "16:00",
                "Triennale Milano",
                "Cutting-edge design & art exhibitions inside an elegant 1930s palace."
            )
        )
    ),

    DayPlan(
        "11 July", listOf(
            Place(
                "09:00",
                "Navigli Canals",
                "Morning espresso along the canals; colorful shutters and vintage bookstores all around."
            ),
            Place(
                "11:30",
                "Fondazione Prada",
                "Contemporary art spaces in a former distillery; don’t miss the gold-leaf tower."
            ),
            Place(
                "14:30",
                "Eataly Smeraldo",
                "Gourmet market for tasting cheeses, wines and regional delicacies."
            ),
            Place(
                "17:30",
                "San Siro Stadium Tour",
                "Legendary home of AC Milan and Inter — locker rooms, pitch side walk and museum."
            )
        )
    )
)