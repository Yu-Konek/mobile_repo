package com.capstone.yukonek.detailyoutuber.data

import com.google.gson.annotations.SerializedName

data class MResponseDetailChannel(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("pageInfo")
	val pageInfo: PageInfo? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class ItemsItem(

	@field:SerializedName("snippet")
	val snippet: Snippet? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("statistics")
	val statistics: Statistics? = null
)

data class Localized(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class Snippet(

	@field:SerializedName("customUrl")
	val customUrl: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("localized")
	val localized: Localized? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: Thumbnails? = null
)

data class Thumbnails(

	@field:SerializedName("default")
	val jsonMemberDefault: JsonMemberDefault? = null,

	@field:SerializedName("high")
	val high: High? = null,

	@field:SerializedName("medium")
	val medium: Medium? = null
)

data class Statistics(

	@field:SerializedName("videoCount")
	val videoCount: String? = null,

	@field:SerializedName("subscriberCount")
	val subscriberCount: String? = null,

	@field:SerializedName("viewCount")
	val viewCount: String? = null,

	@field:SerializedName("hiddenSubscriberCount")
	val hiddenSubscriberCount: Boolean? = null
)

data class JsonMemberDefault(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Medium(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class High(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class PageInfo(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("resultsPerPage")
	val resultsPerPage: Int? = null
)
