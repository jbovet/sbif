/*
 *
 *  * SBIF Client: Java/Groovy API client implementation for
 *    Superintendencia de Bancos e Instituciones Financieras de Chile
 *  * Copyright (C) 2015  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *  *
 *  * This program is free software; you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation; either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package cl.kleedy.sbif.api

/**
 * Created by josebovet on 7/23/15.
 */
class EuroClientSpec extends SBIFClientSpec {

    EuroOperations client

    void setup() {
        client = sbifTemplate.getEuroOperations()
    }

    void "should retrieve today Euro price"() {
        when:
        def euro = client.getEuro()

        then:
        euro.valor >= 600
        euro.fecha != null
    }

    void "should retrieve Euro from specific year 2015"() {
        when:
        def list = client.getEuroByYear(2015)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro from specific from 5/2015"() {
        when:
        def list = client.getEuroByYearAndMonth(2015, 5)

        then:
        list.size() >= 1
    }


    void "should retrieve Euro from specific from 5/5/2015"() {
        when:
        def euro = client.getEuroByYearAndMonthAndDay(2015, 5, 5)

        then:
        euro.valor >= 500
    }

    void "should retrieve Euro greater than 2013"() {
        when:
        def list = client.getEuroLaterYear(2013)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro greater than 2013-05"() {
        when:
        def list = client.getEuroLaterYearAndMonth(2013, 5)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro greater than 2013-5-5"() {
        when:
        def list = client.getEuroLaterYearAndMonthAndDay(2014, 5, 5)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro previous 2015"() {
        when:
        def list = client.getEuroByPreviousYear(2015)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro previous 2015-5"() {
        when:
        def list = client.getEuroByPreviousYearAndMonth(2015, 5)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro previous 2015-5-5"() {
        when:
        def list = client.getEuroByPreviousYearAndMonthAndDay(2015, 5, 5)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro from 2014/2015"() {
        when:
        def list = client.getEuroByPeriod(2014, 2015)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro from 2014/5- 2015/5"() {
        when:
        def list = client.getEuroByPeriod(2014, 5, 2015, 5)

        then:
        list.size() >= 1
    }

    void "should retrieve Euro from 2014/5/5- 2015/5/5"() {
        when:
        def list = client.getEuroByPeriod(2014, 5, 5, 2015, 5, 5)

        then:
        list.size() >= 1
    }
}
