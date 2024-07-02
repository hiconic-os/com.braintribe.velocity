// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.web.velocity.renderer.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Supplier;



public class TemplateProvider implements Supplier<String> {
	private String source;
	private String encoding;
	

	public void setSource(String source) {
		this.source = source;
	}	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String get() throws RuntimeException {
		try {								
			URL url = getClass().getClassLoader().getResource( source);
									
			InputStream in = url.openStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			byte[] buf = new byte[ 1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
			    out.write(buf, 0, count);
			}					
			return out.toString( encoding);
		} catch (Exception e) {
			String msg="cannot provide contents of URL [" + source + "]"; 
			throw new RuntimeException( msg, e);
		} 
			
	}
}
