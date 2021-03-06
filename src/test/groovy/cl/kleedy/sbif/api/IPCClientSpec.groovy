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
class IPCClientSpec extends SBIFClientSpec {

    IPCOperations client

    void setup() {
        client = sbifTemplate.getIpcOperations()
    }

    void "should retrieve IPC from actual month"() {
        when:
        def ipc = client.getIPC()

        then:
        ipc.valor >= 0
        ipc.fecha != null
    }

    void "should retrieve ipc list from 2014"() {
        when:
        def ipc = client.getIPCByYear(2014)

        then:
        ipc.size() == 12
    }

    void "should retrieve ipc list from 2015"() {
        when:
        def ipc = client.getIPCLaterYear(2014)

        then:
        ipc.size() > 0
    }

    void "should retrieve ipc for 6/2014"() {
        when:
        def ipc = client.getIPCByLaterYearAndMonth(2014, 6)

        then:
        ipc.size() > 0
    }

    void "should retrieve ipc previous 2015"() {
        when:
        def ipc = client.getIPCByPreviousYear(2015)

        then:
        ipc.size() > 0
    }

    void "should retrieve ipc previous 5/2014"() {
        when:
        def ipc = client.getIPCByPreviousYearAndMonth(2015, 5)

        then:
        ipc.size() > 0
    }

    void "should retrieve ipc for 5/2014 6/2015"() {
        when:
        def ipc = client.getIPCByPeriodYearAndMonth(2014, 5, 2015, 6)

        then:
        ipc.size() > 0
    }

    void "should retrieve ipc for 2013 -2014"() {
        when:
        def ipc = client.getIPCPeriodos(2013, 2014)

        then:
        ipc.size() > 0
    }


}
