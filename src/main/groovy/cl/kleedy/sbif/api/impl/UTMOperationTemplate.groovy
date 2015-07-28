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
import cl.kleedy.sbif.api.SBIFClientException
import cl.kleedy.sbif.api.UTMOperations
import cl.kleedy.sbif.api.indicadores.UTM
import wslite.rest.RESTClient

/**
 * Operaciones Unidad Tributaria Mensual
 * Created by josebovet on 7/15/15.
 */
class UTMOperationTemplate extends BasicOperations implements UTMOperations {

    UTMOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    UTM getUTM() throws SBIFClientException {
        return getResource([:], 'utm', 'getUTMs').first()
    }

    @Override
    List<UTM> getUTMByYear(int year) throws SBIFClientException {
        return getUTMByYearAndMonth(year, 0)
    }

    @Override
    List<UTM> getUTMByYearAndMonth(int year, int month) throws SBIFClientException {
        def params = ['year': year]
        if (month) params.month = month
        return getResource(params, 'utm', 'getUTMs')
    }

    @Override
    List<UTM> getUTMLaterYear(int year) throws SBIFClientException {
        return getUTMLaterYearAndMonth(year, 0)
    }

    @Override
    List<UTM> getUTMLaterYearAndMonth(int year, int month) throws SBIFClientException {
        def params = ['year': year]
        if (month) params.month = month
        return getResource(params, 'utm', '/posteriores', 'getUTMs')
    }

    @Override
    List<UTM> getUTMByPreviousYear(int year) throws SBIFClientException {
        return getUTMByPreviousYearAndMonth(year, 0)
    }

    @Override
    List<UTM> getUTMByPreviousYearAndMonth(int year, int month) throws SBIFClientException {
        def params = ['year': year]
        if (month) params.month = month
        return getResource(params, 'utm', '/anteriores', 'getUTMs')
    }

    @Override
    List<UTM> getUTMByPeriodYearAndMonth(int year, int month, int year2, int month2) throws SBIFClientException {
        Map params = ['year': year, 'month': month, 'year2': year2, 'month2': month2]
        return getResource(params, 'utm', '/periodo', 'getUTMs')
    }

    @Override
    List<UTM> getUTMByPeriods(int year, int year2) throws SBIFClientException {
        Map params = ['year': year, 'year2': year2]
        return getResource(params, 'utm', '/periodo', 'getUTMs')
    }

    /***
     * Llama al recurso UTM, parseando la respuesta en formato json
     * @param url
     * @return List <UTM>
     */
    private List<UTM> getUTMs(String url) throws SBIFClientException {
        def utmList = call(url).json.UTMs
        def list = []
        utmList.each() { data ->
            def v = data.Valor.trim()
            if (v.any { it == ',' }) {
                v = v.substring(0, v.indexOf(','))
            }

            v = (v != null && v.length() > 0) ? Double.parseDouble(v.replace(".", '')) : 0
            list << new UTM(valor: v, fecha: Date.parse('yyyy-MM-dd', data.Fecha))
        }
        return list

    }
}
