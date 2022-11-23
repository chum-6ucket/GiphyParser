package com.chum_6ucket.giphyparser.data.models

data class GiphyResponse(
    val data: List<DataItem>,
    val pagination: Pagination,
    val meta: Meta
)

data class DataItem(
    val id: String,
    val title: String,
    val images: Images
)

data class Images(
    val fixedHeight: ImageRendition,
    val original: ImageRendition,
    val downsized_large: ImageRendition,
    val fixed_width: ImageRendition
)

data class ImageRendition(
    val url: String
)

data class Pagination(
    val totalCount: Int,
    val count: Int,
    val offset: Int
)

data class Meta(
    val status: Int,
    val message: String,
    val responseId: String
)