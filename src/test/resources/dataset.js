db = connect( 'mongodb://root:example@localhost:27017/UnilocalTest?authSource=admin' );
db.cliente.insertMany([
    {
        _id: 'Usuario1',
        nombre: 'Juan',
        correo: 'juan@email.com',
        contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
        nickname: 'juanito',
        urlFotoPerfil: 'mi foto',
        ciudadResidencia: 'ARMENIA',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        _class: 'co.edu.uniquindio.unilocal.model.Cliente'
    },
    {
        _id: 'Usuario2',
        nombre: 'Andres',
        correo: 'andres@email.com',
        contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
        nickname: 'andres123',
        urlFotoPerfil: 'mi foto',
        ciudadResidencia: 'PEREIRA',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        _class: 'co.edu.uniquindio.unilocal.model.Cliente'
    },
    {
        _id: 'Usuario3',
        nombre: 'Sergio',
        correo: 'sergio@email.com',
        contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
        nickname: 'sergio123',
        urlFotoPerfil: 'mi foto',
        ciudadResidencia: 'MANIZALES',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        _class: 'co.edu.uniquindio.unilocal.model.Cliente'
    },
    {
        _id: 'Usuario4',
        nombre: 'Ana',
        correo: 'ana@email.com',
        contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
        nickname: 'anita',
        urlFotoPerfil: 'mi foto',
        ciudadResidencia: 'CALI',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        _class: 'co.edu.uniquindio.unilocal.model.Cliente'
    },
    {
        _id: 'Usuario5',
        nombre: 'Fernanda',
        correo: 'fer@email.com',
        contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
        nickname: 'fer',
        urlFotoPerfil: 'mi foto',
        ciudadResidencia: 'ARMENIA',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        _class: 'co.edu.uniquindio.unilocal.model.Cliente'
    }
]);
db.negocio.insertMany([
    {
        _id: 'Negocio1',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        imagenes: ['imagen1', 'imagen2'],
        telefonos: ['1234567', '7654321'],
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        idUsuario: 'Usuario1',
        horarioNegocio: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        tipoNegocio: 'RESTAURANTE',
        ciudad: 'ARMENIA',
        _class: 'co.edu.uniquindio.unilocal.model.Negocio'
    },
    {
        _id: 'Negocio2',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        imagenes: ['imagen1', 'imagen2'],
        telefonos: ['1234567', '7654321'],
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        idUsuario: 'Usuario2',
        horarioNegocio: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        tipoNegocio: 'RESTAURANTE',
        ciudad: 'PEREIRA',
        _class: 'co.edu.uniquindio.unilocal.model.Negocio'
    },
    {
        _id: 'Negocio3',
        nombre: 'Hotel Armenia',
        descripcion: 'Hotel Armenia',
        imagenes: ['imagen1', 'imagen2'],
        telefonos: ['1234567', '7654321'],
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        idUsuario: 'Usuario3',
        horarioNegocio: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        tipoNegocio: 'HOTEL',
        ciudad: 'ARMENIA',
        _class: 'co.edu.uniquindio.unilocal.model.Negocio'
    },
    {
        _id: 'Negocio4',
        nombre: 'Panda',
        descripcion: 'Disco Bar Panda',
        imagenes: ['imagen1', 'imagen2'],
        telefonos: ['1234567', '7654321'],
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        idUsuario: 'Cliente4',
        horarioNegocio: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        tipoNegocio: 'BAR',
        ciudad: 'PEREIRA',
        _class: 'co.edu.uniquindio.unilocal.model.Negocio'
    },
    {
        _id: 'Negocio5',
        nombre: 'Museo del Oro Quimbaya',
        descripcion: 'Museo del Oro Quimbaya',
        imagenes: ['imagen1', 'imagen2'],
        telefonos: ['1234567', '7654321'],
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        idUsuario: 'Usuario5',
        horarioNegocio: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        tipoNegocio: 'MUSEO',
        ciudad: 'ARMENIA',
        _class: 'co.edu.uniquindio.unilocal.model.Negocio'
    }
]);
db.resenia.insertMany([
    {
        mensaje: "Excelente sitio, muy buena atención",
        fecha: new Date(),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'Negocio1',
        calificacion: 5,
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    }
]);
db.resenia.insertMany([
    {
        descripcion: "Excelente sitio, muy buena atención",
        calificacion: 5,
        idUsuario: 'Usuario2',
        idNegocio: 'Negocio4',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    },
    {
        descripcion: "Excelente sitio, muy buena atención",
        calificacion: 4,
        idUsuario: 'Usuario1',
        idNegocio: 'Negocio5',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    },
    {
        descripcion: "Mal servicio",
        calificacion: 3,
        idUsuario: 'Usuario3',
        idNegocio: 'Negocio2',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    },
    {
        descripcion: "Excelente sitio, muy buena atención",
        calificacion: 5,
        idUsuario: 'Usuario4',
        idNegocio: 'Negocio1',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    },
    {
        descripcion: "Excelente sitio, muy buena atención",
        calificacion: 5,
        idUsuario: 'Usuario5',
        idNegocio: 'Negocio3',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocal.model.Resenia'
    }
]);
    db.moderador.insertMany([
        {
            _id: 'Moderador1',
            nombre: 'Ximena',
            correo: 'angieXimena26@email.com',
            contrasenia: '$2a$10$UhRcJ0mAXMKjBS3qBOMlDuYm7FZSPCj.Jr2fWZX05lGZUZgiLfrgG',
            estadoRegistro: 'ACTIVA',
            _class: 'co.edu.uniquindio.unilocal.model.Moderador'
        },
]);
