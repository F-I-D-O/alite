/* 
 * Copyright (C) 2019 Czech Technical University in Prague.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cz.cvut.fel.aic.alite.common.capability;

import java.io.Serializable;

class CapabilityRecord implements Serializable{
	
	private static final long serialVersionUID = -6082625766262200377L;
	
	public final String identity;
	public final String capabilityName;
	
	public CapabilityRecord(String identity, String capabilityName){
		this.identity = identity;
		this.capabilityName = capabilityName;
	}
}