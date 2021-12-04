package greeting

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful greeting scenario without params
""")
    request {
        method GET()
        url('/greeting')
    }
    response {
        status OK()
        body(
                id: anyNumber(),
                content: "Hello, World!",
                template: "Hello, %s!",
                date: anyDate()
			)
        headers {
            contentType(applicationJson())
        }
    }
}