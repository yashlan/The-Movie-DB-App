/*
 * Created by Muhammad Yashlan Iskandar on 7/7/22, 9:40 PM
 * Last modified 7/7/22, 9:40 PM
 */

package com.yashlan.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class MyIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(NamingPatternDetector.ISSUE_NAMING_PATTERN)

    override val api = CURRENT_API
}