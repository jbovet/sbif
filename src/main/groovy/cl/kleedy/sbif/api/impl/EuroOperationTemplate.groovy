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
import cl.kleedy.sbif.api.EuroOperations
import cl.kleedy.sbif.api.indicadores.Euro
import wslite.rest.RESTClient

/**
 * Operaciones con EURO
 * Created by josebovet on 7/23/15.
 */
class EuroOperationTemplate extends BasicOperations implements EuroOperations{

    EuroOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    Euro getEuro() {
        return getResource([:], 'euro', 'getEuros').first()
    }

    @Override
    List<Euro> getEuroByYear(int year) {
        return getEuroByYearAndMonth(year,0)
    }

    @Override
    List<Euro> getEuroByYearAndMonth(int year, int month) {
        def params = ['year': year]
        if (month) params.month = month
        return getResource(params, 'euro', 'getEuros')
    }

    @Override
    Euro getEuroByYearAndMonthAndDay(int year, int month, int day) {
        Map params = ['year': year, 'month': month, 'day': day]
        return getResource(params, 'euro', 'getEuros').first()
    }

    @Override
    List<Euro> getEuroLaterYear(int year) {
        return getEuroLaterYearAndMonth(year,0)
    }

    @Override
    List<Euro> getEuroLaterYearAndMonth(int year, int month) {
        getEuroLaterYearAndMonthAndDay(year,month,0)
    }

    @Override
    List<Euro> getEuroLaterYearAndMonthAndDay(int year, int month, int day) {
        def params = ['year': year]
        if (month) params.month = month
        if (day) params.day = day
        return getResource(params, 'euro', 'getEuros')
    }

    /****
     * Llama al recurso euro, parseando una lista en formato json
     * @param url
     * @return List<Euro>
     */
    private List<Euro> getEuros(String url) {
        def euros = call(url).json.Euros
        def list = []
        euros.each() { data ->
            list << new Euro(fecha: Date.parse('yyyy-MM-dd', data.Fecha),
                    valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0)
        }
        return list
    }


}
