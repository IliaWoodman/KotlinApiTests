import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.PathNotFoundException
import tags.TeamTags
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8

// немного процедурки. из градла запускается ./gradlew retrofit:executeNotificator
fun main(args: Array<String>) {
    var path = "retrofit/build/reports/allure-report/data/test-cases"

    if (args.isNotEmpty() && args[0] == "gradle") {
        path = "build/reports/allure-report/data/test-cases"
    }
    val result = mutableMapOf<String, Int>()

    TeamTags::class.java.declaredFields.filterNot { it.name == "INSTANCE" }.forEach {
        result[it.get(TeamTags).toString()] = 0
    }

    val dir = File(path)
    val files = dir.listFiles() ?: return
    var status: String?
    var owner: String?
    for (f in files.filter { it.name.endsWith(".json") }) {
        val content = f.readText(UTF_8)
        try {
            status = JsonPath.read(content, "\$.status")
        } catch (ignore: PathNotFoundException) {
            // Добавить логирование
            println("status not found")
            continue
        }
        try {
            owner = JsonPath.read(content, "\$.extra.owner")
        } catch (ignore: PathNotFoundException) {
            // Добавить логирование
            // и логику которая будет формировать сообщение о том что такие то тесты без тега @Owner(TeamName)
            println("owner not found")
            continue
        }

        if (status == "failed" || status == "broken") {
            result.put(owner!!, result.getValue(owner!!) + 1)
        }
    }

    result.forEach { (k, v) ->
        println("Team - $k has $v tests")
    }
// Здесь будет формироваться сообщение, которое отправится в слак: тегнет команды и оповестит о количестве упавших тестов в прогоне
}

