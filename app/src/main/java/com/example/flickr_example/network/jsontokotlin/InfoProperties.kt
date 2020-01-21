package com.example.flickr_example.network.jsontokotlin

data class InfoProperties(
    val photo: PhotoInfo,
    val stat: String
)

data class PhotoInfo(
    val comments: Comments,
    val dates: Dates,
    val dateuploaded: String,
    val description: Description,
    val editability: Editability,
    val farm: Int,
    val id: String,
    val isfavorite: Int,
    val license: Int,
    val media: String,
    val notes: Notes,
    val originalformat: String?,
    val originalsecret: String?,
    val owner: Owner,
    val people: People,
    val publiceditability: Publiceditability,
    val rotation: Int,
    val safety_level: Int,
    val secret: String,
    val server: String,
    val tags: Tags,
    val title: Title,
    val urls: Urls,
    val usage: Usage,
    val views: Int,
    val visibility: Visibility
)


data class Comments(
    val _content: Int
)


data class Dates(
    val lastupdate: String,
    val posted: String,
    val taken: String,
    val takengranularity: Int,
    val takenunknown: Int
)


data class Description(
    val _content: String
)


data class Editability(
    val canaddmeta: Int,
    val cancomment: Int
)


data class Notes(
    val note: List<Any>
)


data class Owner(
    val iconfarm: Int,
    val iconserver: String,
    val location: String,
    val nsid: String,
    val path_alias: String?,
    val realname: String,
    val username: String
)


data class People(
    val haspeople: Int
)


data class Publiceditability(
    val canaddmeta: Int,
    val cancomment: Int
)


data class Tags(
    val tag: List<Any>
)


data class Title(
    val _content: String
)


data class Urls(
    val url: List<Url>
)


data class Url(
    val _content: String,
    val type: String
)


data class Usage(
    val canblog: Int,
    val candownload: Int,
    val canprint: Int,
    val canshare: Int
)


data class Visibility(
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int
)