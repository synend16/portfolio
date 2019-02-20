package com.synend.portfolio.models

class GitHubRepositoryResponse(

        var list: List<GitHubRepository>? = null

)


class GitHubRepository(
        var id: String? = null,

        var name: String? = null,

        var svn_url: String? = null,

        var topics: List<String>? = mutableListOf(),

        var description: String? = null,

        var updated_at: String? = null
)