function confirmarEliminarCarta(opcion) {
    event.preventDefault();
    Swal.fire({
        title: 'Confirmar solicitud',
        text: "¿Está seguro que desea eliminar este plato?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '¡Sí, eliminar!',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                    '¡Plato eliminado!',
                    'El plato fue eliminado con éxito.',
                    'success'
                    ).then((result) => {
                if (result.isConfirmed)
                {
                    location.href = opcion.href;
                }
            });
        }
    });
}