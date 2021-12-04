package greeting

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        urlPath('/greeting')  {
            queryParameters {
                    parameter('''name''', '''Spring Community''')
            }
        } 
    }
    response {
        status 200
        body(
                id:anyNumber(),
                content:"Hello, Spring Community!",
                template:"Hello, %s!",
                date:"2021-12-04"
        )
        headers {
            header('''Content-Type''', '''application/json''')
        }
    }
}
