--------------------------------------------------------------------------------
Procedimiento almacenado de inserción de nuevos usuarios x bitacoras

DELIMITER $$

CREATE PROCEDURE insertXUsuariosXBitacXAccionInsert (
    IN p_id_usuario INT,
    IN p_nombre_usuario VARCHAR(255),
    IN p_contrasenia VARCHAR(255),
    IN p_dtvigencia DATE,
    IN p_id_perfil INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT,
    IN p_sucursal_sistema VARCHAR(255)
)
BEGIN
    -- 2. Insertar el nuevo usuario con la contraseña encriptada usando MD5
    INSERT INTO tbl_usuarios (id_usuario, Nombre, vchPass, dtVigencia, id_perfil)
    VALUES (p_id_usuario, p_nombre_usuario, MD5(p_contrasenia), p_dtvigencia, p_id_perfil);
    
    -- 4. Insertar en la bitácora la acción de creación del nuevo usuario
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' creó al usuario: ', p_id_usuario));
            
    -- 5. Insertar en la bitácora la acción del usuario que se acaba de crear
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, p_nombre_usuario, p_id_perfil, 'Nuevo usuario creado.');
    
END $$

DELIMITER ;


--------------------------------------------------------------------------------
Procedimiento almacenado de actualizar usuario x bitacoras

DELIMITER $$

CREATE PROCEDURE updateXUsuariosXBitacXAccionUpdate (
    IN p_id_perfil INT, 
    IN p_id_usuario INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    IN p_sucursal_sistema VARCHAR(255),
    
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el nombre del usuario.
    DECLARE nombre_usuario VARCHAR(255);
    
    -- 2. Actualizar el perfil del usuario en la tabla tbl_usuarios.
    UPDATE tbl_usuarios 
    SET id_perfil = p_id_perfil 
    WHERE id_usuario = p_id_usuario;
    
    -- 3. Con ayuda del id_usuario que se actualizó en el paso 1, mandar a traer el Nombre del usuario y almacenarlo en la variable nombre_usuario creada en el paso 1.
    SELECT Nombre INTO nombre_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;
    
    -- 4. Insertar en la bitácora la acción del usuario que realizó la actualización.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Actualizó al usuario: ', p_id_usuario));
            
    -- 5. Insertar en la bitácora la acción de la actualización del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, nombre_usuario, p_id_perfil, 'Usuario actualizó datos');
    
END $$

DELIMITER ;


-----------------------------------------------------------------------------------------------------------------
procedimiento de restablecer contraseña usuario x bitacoras

DELIMITER $$

CREATE PROCEDURE resetPasswXUsuariosXBitacXAccionReset (
    IN p_contraseniaEncriptada VARCHAR(255),
    IN p_fechaVigencia VARCHAR(255),
    IN p_id_usuario INT,
    IN P_nombre_usuario VARCHAR(255),
    IN p_sucursalNuevoUsuario VARCHAR(255),
    
    IN p_sucursal_sistema VARCHAR(255),
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el id_perfil del usuario
    DECLARE p_id_perfil_usuario INT;
    
    -- 2. Restablecer la contraseña del usuario en la tabla tbl_usuarios.
    UPDATE tbl_usuarios 
    SET vchPass = p_contraseniaEncriptada, dtVigencia = p_fechaVigencia 
    WHERE id_usuario = p_id_usuario;

    -- 3. Con ayuda del id_usuario que se restableció en el paso 2, recuperamos el id_perfil y lo almacenamos en la variable p_id_perfil_usuario
    SELECT id_perfil INTO p_id_perfil_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;
    
    -- 4. Insertar en la bitácora la acción del usuario que realizó el restablecimiento de contraseña
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Restablecio la contraseña al usuario: ', p_id_usuario));
            
    -- 5. Insertar en la bitácora la acción del restablecimiento de la contraseña del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, P_nombre_usuario, p_id_perfil_usuario, 'Restablecio su contraseña');


END $$

DELIMITER ;


--------------------------------------------------------------------------------
procedimiento almacenado de elminar usuarios x bitacoras

DELIMITER $$

CREATE PROCEDURE deleteUsersXBitacXAccionDeleteUser (
    IN p_id_usuario INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    IN p_sucursal_sistema VARCHAR(255),
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el nombre y el perfil del usuario.
    DECLARE p_id_perfil_usuario INT;
    DECLARE p_nombre_usuario VARCHAR(255);
    
    -- 2. Con ayuda del id_usuario, recuperamos el nombre y el perfil del usuario.
    SELECT id_perfil, Nombre INTO p_id_perfil_usuario, p_nombre_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;

    -- 3. Insertar en la bitácora la acción del usuario que realizó la eliminación.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Elimino a usuario: ', p_id_usuario));
    
    -- 4. Insertar en la bitácora la acción de eliminación del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, p_nombre_usuario, p_id_perfil_usuario, 'Eliminado del sistema');

    -- 5. Eliminar el usuario de la tabla tbl_usuarios.
    DELETE FROM tbl_usuarios WHERE id_usuario = p_id_usuario;

END $$

DELIMITER ;

--------------------------------------------------------------------------------
Consulta en donde se agregan para las tablas, los datos de Invitado, Telefono, FechaCompra, FechaVigencia


    SELECT
	b.OrigenUsuario AS Sucursal,
        b.OrigenSocio AS Sucursal2,
        b.Folio,
        b.Origen,
        b.Grupo,
        b.NumSocio,
        b.Nombre,
        b.Invitado,
        b.Telefono,
        u.Nombre AS Cajero,
        z.Zona,
        b.Costo AS Precio_Boleto,
        m.DescMesa AS Mesa,
        s.vchDescripcion AS Silla,
        b.FechaCompra,
        b.FechaVigencia
        FROM tbl_boletos b
        INNER JOIN tbl_usuarios u ON b.id_usuario = u.id_usuario
        LEFT JOIN tbl_zonas z ON b.idZona = z.idZona
        LEFT JOIN tbl_mesas m ON b.idMesa = m.idMesa
        LEFT JOIN tbl_sillas s ON b.idSilla = s.idSilla
        WHERE 1=1 



