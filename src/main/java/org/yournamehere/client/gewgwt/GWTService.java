/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.gewgwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author mr_user
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {

    public String myMethod(String s);
}
