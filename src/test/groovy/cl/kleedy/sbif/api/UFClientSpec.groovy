package cl.kleedy.sbif.api

/**
 * Created by josebovet on 7/30/15.
 */
class UFClientSpec extends SBIFClientSpec {

    UFOperations client

    void setup() {
        client = sbifTemplate.getUfOperations()
    }

    void "should retrieve today UF"() {
        when:
        def uf = client.getUF()
        uf.valor >= 24000

        then:
        uf.fecha != null
    }

    void "should retrieve UF list from 2015"() {
        when:
        def ufList = client.getUFByYear(2015)

        then:
        ufList.size() >= 0
    }

    void "should retrieve UF list from 2015-05"() {
        when:
        def ufList = client.getUFByYear(2015,5)

        then:
        ufList.size() == 31
    }

    void "should retrieve UF list from 2015-5-5"() {
        when:
        def uf = client.getUFByYear(2015,5,5)

        then:
        uf.valor >= 24779.0
    }

    void "should retrieve UF list later from 2014"() {
        //TODO test with 2015 404
        when:
        def ufList = client.getUFLaterYear(2014)

        then:
        ufList.size()==221
    }

    void "should retrieve UF list later from 2014-5"() {
        when:
        def ufList = client.getUFLaterYear(2014,5)

        then:
        ufList.size()>=0
    }

    void "should retrieve UF list later from 2014-5-6"() {
        when:
        def uf = client.getUFLaterYear(2014,5,5)

        then:
        uf.valor >= 0
    }
}
