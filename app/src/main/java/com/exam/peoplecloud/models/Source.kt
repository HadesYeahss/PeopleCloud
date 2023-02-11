package com.exam.peoplecloud.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable


@Root(name = "source", strict = false)
data class Source(
    @field:Element(name = "publisher")
    @param:Element(name = "publisher")
    val publisher: String,

    @field:Element(name = "publisherurl")
    @param:Element(name = "publisherurl")
    val publisherurl: String,

    @field:ElementList(inline = true,entry = "job")
    @param:ElementList(inline = true,entry = "job")
    val jobP: List<JobP>
)

class JobP(
    @field:Element(name = "title", required = false)
    @param:Element(name = "title", required = false)
    val title: String? = null,

    @field:Element(name = "date", required = false)
    @param:Element(name = "date", required = false)
    val date: String? = null,

    @field:Element(name = "referencenumber", required = false)
    @param:Element(name = "referencenumber", required = false)
    val referencenumber: Int? = null,

    @field:Element(name = "url", required = false)
    @param:Element(name = "url", required = false)
    val url: String? = null,

    @field:Element(name = "company", required = false)
    @param:Element(name = "company", required = false)
    val company: String? = null,

    @field:Element(name = "city", required = false)
    @param:Element(name = "city", required = false)
    val city: String? = null,

    @field:Element(name = "state", required = false)
    @param:Element(name = "state", required = false)
    var state: String? = null,

    @field:Element(name = "country", required = false)
    @param:Element(name = "country", required = false)
    val country: String? = null,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    val description: String? = null,

    @field:Element(name = "indeed-apply-data", required = false)
    @param:Element(name = "indeed-apply-data", required = false)
    val indeedapplydata: String? = null
):Serializable
