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
import cl.kleedy.sbif.api.DolarOperations
import cl.kleedy.sbif.api.SBIFClientException
import cl.kleedy.sbif.api.indicadores.Dolar
import wslite.rest.RESTClient

/**
 * Operaciones para el dolar
 * Created by josebovet on 7/14/15.
 */
class DolarOperationTemplate extends BasicOperations implements DolarOperations {

    DolarOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    List<Dolar> getDolares(int year) throws SBIFClientException {
        Map params = ['year': year]
        return getResource(params, "dolar", "getDollars")
    }

    @Override
    List<Dolar> getDolares() throws SBIFClientException {
        Map params = [:]
        def instance = Calendar.getInstance();
        params << ['year': instance.get(Calendar.YEAR)]
        return getResource(params, 'dolar', 'getDollars')
    }

    @Override
    List<Dolar> getDolares(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', 'getDollars')
    }

    @Override
    Dolar getDolares(int year, int month, int day) throws SBIFClientException {
        Map params = ['year': year, 'month': month, 'day': day]
        return getResource(params, 'dolar', 'getDollars').first()
    }

    @Override
    List<Dolar> getDolaresPosteriores(int year) throws SBIFClientException {
        return getResource(['year': year], 'dolar', '/posteriores', 'getDollars')
    }

    @Override
    List<Dolar> getDolaresPosteriores(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', '/posteriores', 'getDollars')
    }

    @Override
    List<Dolar> getDolaresAnteriores(int year) throws SBIFClientException {
        return getResource(['year': year], 'dolar', '/anteriores', 'getDollars')
    }

    @Override
    List<Dolar> getDolaresAnteriores(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', '/anteriores', 'getDollars')
    }

    @Override
    List<Dolar> getDolaresPeriodo(int year, int year2) throws SBIFClientException {
        Map params = ['year': year, 'year2': year2]
        return getResource(params, 'dolar', '/periodo', 'getDollars')

    }

    @Override
    Dolar getDolar() throws SBIFClientException {
        def instance = Calendar.getInstance();
        Map params = [:]
        params << ['year': instance.get(Calendar.YEAR)]
        params << ['mont': instance.get(Calendar.MONTH) + 1]
        params << ['day': instance.get(Calendar.DAY_OF_MONTH)]
        return getResource(params, 'dolar', 'getDollars').first()
    }

    /****
     * Llama al recurso dolar,parseando una lista en formato json
     * @param url
     * @return List <Dolar>
     */
    private List<Dolar> getDollars(String url) throws SBIFClientException {
        def dolares = call(url).json.Dolares
        def list = []
        dolares.each() { data ->
            list << new Dolar(fecha: Date.parse('yyyy-MM-dd', data.Fecha),
                    valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0)
        }
        return list
    }
}
