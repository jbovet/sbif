package cl.kleedy.sbif.api

import cl.kleedy.sbif.api.impl.DolarOperationTemplate
import spock.lang.Specification
import wslite.http.HTTPClientException
import wslite.rest.RESTClient
import wslite.rest.Response

/**
 * Created by josebovet on 7/28/15.
 */
class ClientSpec extends Specification {

    RESTClient mockRestClient = Mock()
    Response mockResponse = Mock()

    DolarOperations dolarOperations


    void setup() {
        dolarOperations = new DolarOperationTemplate(mockRestClient, '')
    }

    void "should handle communication error on retrieving dolars"() {
        when:
        dolarOperations.getDolar()

        then:
        mockRestClient.get(_) >> { throw new HTTPClientException("cueck!") }

        and:
        thrown(SBIFClientException)
    }
}

