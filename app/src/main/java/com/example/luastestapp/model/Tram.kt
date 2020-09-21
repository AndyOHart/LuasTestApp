package com.example.luastestapp.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Tram(@Attribute val destination: String, @Attribute val dueMins: String)