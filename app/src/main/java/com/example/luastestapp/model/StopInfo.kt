package com.example.luastestapp.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
class StopInfo(
    @Attribute val created: String,
    @Attribute val stop: String,
    @Attribute val stopAbv: String,
    @PropertyElement val message: String,
    @Element val directions: List<Direction>
)