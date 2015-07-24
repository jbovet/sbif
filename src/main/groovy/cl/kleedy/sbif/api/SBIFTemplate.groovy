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

import cl.kleedy.sbif.api.impl.*
import wslite.rest.RESTClient

/**
 * Template para version 3.0 de la api SBIF.
 * Created by josebovet on 7/14/15.
 * @version 3.0
 */
class SBIFTemplate implements SBIF {

    DolarOperations dolarOperations

    TMCOperations tmcOperations

    IPCOperations ipcOperations

    UTMOperations utmOperations

    EuroOperations euroOperations

    RESTClient restClient

    String apiKey

    /***
     * URL v3
     */
    static final BASE_URL = "http://api.sbif.cl/api-sbifv3/recursos_api";

    /***
     * Constructor
     * @param apikey
     */
    SBIFTemplate(String apikey) {
        restClient = new RESTClient(BASE_URL);
        apiKey = apikey
        initSubApis();
    }


    @Override
    DolarOperations dolarOperations() {
        return dolarOperations
    }

    @Override
    TMCOperations tmcOperations() {
        return tmcOperations
    }

    @Override
    IPCOperations ipcOperations() {
        return ipcOperations
    }

    @Override
    UTMOperations utmOperations() {
        return utmOperations
    }

    @Override
    EuroOperations euroOperations() {
        return euroOperations
    }

    /***
     * inicializa las operaciones permitidas con SBIF
     */
    private void initSubApis() {
        dolarOperations = new DolarOperationTemplate(getRestClient(), getApiKey())
        tmcOperations = new TMCOperationTemplate(getRestClient(), getApiKey())
        ipcOperations = new IPCOperationTemplate(getRestClient(), getApiKey())
        utmOperations = new UTMOperationTemplate(getRestClient(),getApiKey())
        euroOperations = new EuroOperationTemplate(getRestClient(), getApiKey())
    }

    /***
     *
     * @return RESTClient
     */
    RESTClient getRestClient() {
        return restClient
    }

    /***
     *
     * @return apiKey
     */
    String getApiKey() {
        return apiKey
    }
}
