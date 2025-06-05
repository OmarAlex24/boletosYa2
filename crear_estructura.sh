#!/bin/bash

# --- Directorio Base ---
BASE_DIR="src/boletosyappae"
mkdir -p "$BASE_DIR"
echo "Directorio base '$BASE_DIR' creado."

# --- Paquetes Principales ---
DIRECTORIES=(
    "controllers"
    "models"
    "services"
    "dao"
    "exceptions"
    "utils"
    "views"
)

for dir in "${DIRECTORIES[@]}"; do
    mkdir -p "$BASE_DIR/$dir"
    echo "  - Paquete '$dir' creado."
done

# --- Archivos Java y FXML ---

# Main Class
touch "$BASE_DIR/BoletosYaPPAE.java"

# Controllers
CONTROLLERS=(
    "LoginController"
    "MainMenuController"
    "EmpleadosController"
    "AerolineasController"
    "AvionesController"
    "VuelosController"
    "ClientesController"
)
for controller in "${CONTROLLERS[@]}"; do
    touch "$BASE_DIR/controllers/${controller}.java"
done

# Models
MODELS=(
    "Empleado"
    "Administrativo"
    "AsistenteVuelo"
    "Piloto"
    "Aerolinea"
    "Avion"
    "Vuelo"
    "Cliente"
    "Boleto"
)
for model in "${MODELS[@]}"; do
    touch "$BASE_DIR/models/${model}.java"
done

# Services
SERVICES=(
    "EmpleadoService"
    "AerolineaService"
    "AvionService"
    "VueloService"
    "ClienteService"
)
for service in "${SERVICES[@]}"; do
    touch "$BASE_DIR/services/${service}.java"
done

# DAO
DAOS=("DataManager" "JsonFileHandler")
for dao in "${DAOS[@]}"; do
    touch "$BASE_DIR/dao/${dao}.java"
done

# Exceptions
EXCEPTIONS=(
    "CredencialesInvalidasException"
    "CapacidadExcedidaException"
    "VueloNoDisponibleException"
)
for exception in "${EXCEPTIONS[@]}"; do
    touch "$BASE_DIR/exceptions/${exception}.java"
done

# Utils
UTILS=("ExportUtils" "ValidationUtils")
for util in "${UTILS[@]}"; do
    touch "$BASE_DIR/utils/${util}.java"
done

# Views (FXML)
VIEWS=(
    "login"
    "main-menu"
    "empleados"
    "aerolineas"
    "aviones"
    "vuelos"
    "clientes"
)
for view in "${VIEWS[@]}"; do
    touch "$BASE_DIR/views/${view}.fxml"
done

echo ""
echo "¡Estructura del proyecto creada exitosamente!"
