# Consideraciones Generales
- Se entrega un scope con una breve descripción de cada trabajo práctico. Será responsabilidad del alumno el diseño, consideraciones de negocio, diseño de base de datos (de ser necesario).
- Tener en cuenta que no debe haber menos de 4 ni más de 6 entidades en total.
- Tener en cuenta que en esta materia no se evalúa el diseño de base de datos, ni el manejo de SQL. En la materia sí se evalúa el diseño orientado a objetos. Se pone especial énfasis en conceptos vistos en la materia como Acoplamiento/Cohesión y reutilización de código.
- La primera entrega, que cumple la función de segundo parcial, consistirá en un scope acotado, a saber: tomando unaúnica entidad de todo el sistema, se deberá realizar un CRUD completo, incluyendo manejo de excepciones y pantallas en Swing.
- Cada trabajo prácticodeberá ser “defendido” el día de la evaluación final, explicando cuáles fueron las consideraciones de diseño y justificarlas.

# Consideraciones particulares de cada trabajo práctico
1. Los trabajos prácticos, como cualquier instancia de evaluación, se aprueban con 4 (cuatro).
2. Funcionalidad básica: sin esta funcionalidad, al menos, el TP no aprueba. Si está y aplica los conceptos vistos en la materia, entonces se lo considerará simplemente “aprobado” (4 -cuatro).
3. Adicionales: para comenzar a hacer del TP un trabajo más completo y comenzar a sumar por encima de lo entregado en el punto 2.
4. Bonus points: se valorará (solo si el punto 2 y 3 están completos) el esfuerzo extra por cumplir con esos puntos.

# Tema 6: Turnera médica
## Funcionalidad básica:
- Administrar médicos. Cada médico es un usuario del sistema, pudiendo consultar los turnos que tiene para una fecha determinada.
- Cada médico cobra cierta cantidad de dinero por su consulta.
- Administrar pacientes.
- Administrar turnos, fecha y hora. No se puede tomar un turno con un mismo médico a una misma hora. El médico debe elegirse de una lista, al igual que el paciente.
- Reportes: se debe poder obtener una lista de cuánto ha cobrado un médico, por cuántas consultas (turnos) entre dos fechas.
## Adicionales:
- Los pacientes también son usuarios, pudiendo consultar cuándo tienen que asistir a una consulta.
- Administrar lugares de atención (consultorios) con la consecuencia de que un médico debe atender en un cierto lugar entre una y otra fecha, y el turno debe tomarse en un cierto consultorio (es solo una restricción más al turno).
- Reporte adicional: listar los médicos y su recaudación entre dos fechas.
## Bonus points:
- Manejar obras sociales. Si un paciente tiene la misma obra social que la que atiende un médico, se le hace un descuento del 50% en la consulta.
- Mostar una grilla mensual o semanal con turnos (como si se mostrara un calendario).
