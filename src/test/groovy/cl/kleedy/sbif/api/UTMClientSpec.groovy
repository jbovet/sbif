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
 * Created by josebovet on 7/15/15.
 */
class UTMClientSpec extends SBIFClientSpec {

    UTMOperations client

    void setup() {
        client = sbifTemplate.getUtmOperations()
    }

    void "should retrieve actual UTM month"() {
        when:
        def utm = client.getUTM();

        then:
        utm.valor >= 40000
        utm.fecha != null
    }

    void "should retrieve UTM from specific year 2015"() {
        when:
        def list = client.getUTMByYear(2015)

        then:
        list.size() >= 7
    }

    void "should retrieve UTM from specific year 2015 and month 5"() {
        when:
        def list = client.getUTMByYearAndMonth(2015,5)

        then:
        list.size() > 0
    }

    void "should retrieve UTM for 2014-2015 "() {
        when:
        def list = client.getUTMLaterYear(2013)

        then:
        list.size() > 0
    }





}
