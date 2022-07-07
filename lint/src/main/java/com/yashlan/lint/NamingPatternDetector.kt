package com.yashlan.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass

@Suppress("UnstableApiUsage")
class NamingPatternDetector : Detector(), Detector.UastScanner {
    override fun getApplicableUastTypes() = listOf(UClass::class.java)
    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                if (node.name?.isDefinedCamelCase() == false) {
                    context.report(
                        ISSUE_NAMING_PATTERN, node,
                        context.getNameLocation(node),
                        "Penulisan nama class harus menggunakan CamelCase."
                    )
                }
            }
        }
    }

    private fun String.isDefinedCamelCase(): Boolean {
        val charArray = toCharArray()
        return charArray
            .mapIndexed { index, current ->
                current to charArray.getOrNull(index + 1)
            }
            .none {
                it.first.isUpperCase() && it.second?.isUpperCase() ?: false
            }
    }

    companion object {
        val ISSUE_NAMING_PATTERN: Issue = Issue.create(
            id = "NamingPattern",
            briefDescription = "Penulisan nama class harus menggunakan CamelCase.",
            explanation = """
                      Tulis nama class dengan menggunakan CamelCase.
                      Lihat contoh yang benar di https://google.github.io/styleguide/javaguide.html#s5.3-camel-case 
                       """,
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                NamingPatternDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}