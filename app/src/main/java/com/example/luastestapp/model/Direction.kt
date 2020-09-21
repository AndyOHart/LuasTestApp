package com.example.luastestapp.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Direction(@Attribute val name: String, @Element val tram: List<Tram>)