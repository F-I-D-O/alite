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
package cz.cvut.fel.aic.alite.environment;

import cz.cvut.fel.aic.alite.common.entity.Entity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

/**
 * The Environment is a base class for all basic environments.
 *
 * The successors of the Environment class should aggregate and instantiate several {@link Storage}
 * s, which will hold the particular data structures defining the environment and should also
 * provide getters to access the storages. The getters should not be included in the {@link Handler}
 * , since the entities/agents should not have an access to the storages directly.
 *
 * On the contrary, {@link Sensor}s and {@link Action}s created by the entities through the handler
 * will have the access to the getters of the storages by a reference to the environment, so they
 * can read and update the state of the environment represented in the storages.
 *
 * Additionally, the Environment also provides a shared environmental random generator, which should
 * be used for any randomization in the logic of the sensors and actions (if it it so, it is
 * possible to create deterministically reproducible runs of the system - the random seed can be set
 * at one place).
 *
 *
 * @author Antonin Komenda
 */
public abstract class Environment {

	private final Handler handler;
	private Random random = new Random();

	public Environment() {
		handler = new Handler();
	}

	public Environment.Handler handler() {
		return handler;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * The Environment.Handler provides an interface to the environment for the entities (agents)
	 * behaving in the world.
	 *
	 * Since the entities are not typically allowed to access all the properties of an environment,
	 * each environment provides its handler to enable the entities only to create instances of
	 * actions and sensors.
	 *
	 * Additionally, the Handler also provides a shared environmental random generator, which should
	 * be used for any randomization in the logic of the entities/agents (if it it so, it is
	 * possible to create deterministically reproducible runs of the system - the random seed can be
	 * set at one place).
	 *
	 *
	 * @author Antonin Komenda
	 */
	public class Handler {

		protected Handler() {
		}

		public <C extends Action> C addAction(Class<C> clazz, Entity entity) {
			return instantiateEnvironmentClass(clazz, entity, new Class<?>[] { Environment.class,
					Entity.class });
		}

		public <C extends Sensor> C addSensor(Class<C> clazz, Entity entity) {
			return instantiateEnvironmentClass(clazz, entity, new Class<?>[] { Environment.class,
					Entity.class });
		}

		public Random getRandom() {
			return random;
		}

		/**
		 * The method creates a new instance of the <code>clazz</code> in the environment.
		 *
		 * The instance is created using a constructor, which is compatible with the parameter types
		 * in the <code>baseParameterTypesRequired</code> array. By the compatibility, it is meant
		 * the argument must be of the type or of a subtype of the required type.
		 *
		 * @param <C>
		 *			the class type of the instantiated class
		 * @param clazz
		 *			a class literal of the instantiated class
		 * @param entity
		 *			a related entity with the instantiated class
		 * @param baseParameterTypesRequired
		 *			an array of the types according which the constructor should be used
		 * @return the newly instantiated environment class (sensor/action typically)
		 */
		@SuppressWarnings("unchecked")
		protected <C> C instantiateEnvironmentClass(Class<C> clazz, Entity entity,
				Class<?>[] baseParameterTypesRequired) {
			C instance = null;

			try {
				Constructor<C> selectedConstructor = null;
				for (Constructor<?> constructor : clazz.getConstructors()) {
					Class<?>[] parameterTypes = constructor.getParameterTypes();

					// is the constructor usable?
					boolean usable = true;

					// number of parameters has to be the same
					if (baseParameterTypesRequired.length != parameterTypes.length) {
						usable = false;
					} else {
						// the types of constructor parameters has to be compatible with the arguments
						for (int i = 0; i < parameterTypes.length; i++) {
							if (!baseParameterTypesRequired[i].isAssignableFrom(parameterTypes[i])) {
								usable = false;
							}
						}
					}

					// select an usable constructor
					if (usable) {
						selectedConstructor = (Constructor<C>) constructor;
						break;
					}
				}

				if (selectedConstructor != null) {
					instance = selectedConstructor.newInstance(Environment.this, entity);
				} else {
					throw new RuntimeException("Cannot find an usable constructor in class "
							+ clazz.getCanonicalName() + " for base parameter types: "
							+ Arrays.asList(baseParameterTypesRequired));
				}
			} catch (InvocationTargetException e) {
				if (e.getCause() != null) {
					e.getCause().printStackTrace();
				} else {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instance;
		}

	}

}
