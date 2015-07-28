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

package cl.kleedy.sbif.api.impl

import cl.kleedy.sbif.api.BasicOperations
import cl.kleedy.sbif.api.IPCOperations
import cl.kleedy.sbif.api.SBIFClientException
import cl.kleedy.sbif.api.indicadores.IPC
import wslite.rest.RESTClient

/**
 * Operaciones para IPC
 * Created by josebovet on 7/15/15.
 */
class IPCOperationTemplate extends BasicOperations implements IPCOperations {

    IPCOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    IPC getIPC() throws SBIFClientException {
        return getResource([:], 'ipc', 'getIPCS').first()
    }

    @Override
    List<IPC> getIPCByYear(int year) throws SBIFClientException {
        return getResource(['year': year], 'ipc', 'getIPCS')
    }

    @Override
    List<IPC> getIPCLaterYear(int year) throws SBIFClientException {
        return getResource(['year': year], 'ipc', '/posteriores', 'getIPCS')
    }

    @Override
    List<IPC> getIPCByLaterYearAndMonth(int year, int month) throws SBIFClientException {
        return getResource(['year': year, 'month': month], 'ipc', '/posteriores', 'getIPCS')
    }

    @Override
    List<IPC> getIPCByPreviousYear(int year) throws SBIFClientException {
        return getIPCByPreviousYearAndMonth(year, 0)
    }

    @Override
    List<IPC> getIPCByPreviousYearAndMonth(int year, int month) throws SBIFClientException {
        def params = ['year': year]
        if (month) params.month = month
        return getResource(params, 'ipc', '/anteriores', 'getIPCS')
    }

    @Override
    List<IPC> getIPCByPeriodYearAndMonth(int year, int month, int year2, int month2) throws SBIFClientException {
        Map params = ['year': year, 'month': month, 'year2': year2, 'month2': month2]
        return getResource(params, 'ipc', '/periodo', 'getIPCS')
    }

    @Override
    List<IPC> getIPCPeriodos(int year, int year2) throws SBIFClientException {
        Map params = ['year': year, 'year2': year2]
        return getResource(params, 'ipc', '/periodo', 'getIPCS')
    }

    /***
     * Llama al recurso IPC,parseando la respuesta en formato json
     * @param url
     * @return List <IPC>
     */
    private List<IPC> getIPCS(String url) throws SBIFClientException {
        def ipcs = call(url).json.IPCs
        def list = []
        ipcs.each() { data ->
            list << new IPC(valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0,
                    fecha: Date.parse('yyyy-MM-dd', data.Fecha))
        }
        return list
    }
}
