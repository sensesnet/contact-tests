package greeting

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful greeting scenario without params
""")
    request {
        method GET()
        url('/greeting') {
            queryParameters {
                parameter('name', $(consumer(~/.+/), producer('Valerii')))
            }
        }
    }
    response {
        status OK()
        body(
                id: $(consumer(1), producer(anyNumber())),
                content: "Hello, ${fromRequest().query('name')}!",
                template: "Hello, %s!",
                date:$(consumer(anyDate()), producer(isoDate()))
			)
        headers {
            contentType(applicationJson())
        }
    }
}