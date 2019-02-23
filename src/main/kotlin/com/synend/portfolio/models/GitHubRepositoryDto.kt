package com.synend.portfolio.models

import java.io.Serializable

data class GitHubRepositoryDto (
        var id: String? = null,

        var name: String? = null,

        var svn_url: String? = null,

        var topics: MutableList<String>? = mutableListOf(),

        var description: String? = null,

        var pushed_at: String? = null
) : Serializable