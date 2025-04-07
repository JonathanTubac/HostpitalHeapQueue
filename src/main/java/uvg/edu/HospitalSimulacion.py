import simpy
import random
import matplotlib.pyplot as plt

RANDOM_SEED = 10
INTERVALO_LLEGADAS = 5  # Tiempo promedio entre llegadas
TIEMPO_SIMULACION = 200

# Parámetros del hospital
NUM_ENFERMERAS = 2
NUM_DOCTORES = 2
NUM_RAYOSX = 1
NUM_LAB = 1

# Datos para estadísticas
tiempos_espera = []

def paciente(env, nombre, enfermeras, doctores, rayosx, laboratorio):
    llegada = env.now
    print(f"{nombre} llega a la sala a las {env.now:.2f}")

    # TRIAGE
    with enfermeras.request() as req:
        yield req
        yield env.timeout(10)  # 10 unidades de tiempo por paciente
        severidad = random.randint(1, 5)
        print(f"{nombre} evaluado con severidad {severidad} a las {env.now:.2f}")

    # DOCTOR
    with doctores.request(priority=severidad) as req:
        yield req
        yield env.timeout(random.randint(15, 25))  # atención médica
        print(f"{nombre} atendido por doctor a las {env.now:.2f}")

    # Examenes aleatorios
    necesita_rayosx = random.choice([True, False])
    necesita_lab = random.choice([True, False])

    if necesita_rayosx:
        with rayosx.request(priority=severidad) as req:
            yield req
            yield env.timeout(random.randint(5, 10))
            print(f"{nombre} pasó por rayos X a las {env.now:.2f}")

    if necesita_lab:
        with laboratorio.request(priority=severidad) as req:
            yield req
            yield env.timeout(random.randint(7, 15))
            print(f"{nombre} pasó por laboratorio a las {env.now:.2f}")

    salida = env.now
    tiempo_total = salida - llegada
    tiempos_espera.append(tiempo_total)
    print(f"{nombre} sale a las {env.now:.2f} después de {tiempo_total:.2f} unidades de tiempo")


def generador_pacientes(env, enfermeras, doctores, rayosx, laboratorio):
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADAS))
        i += 1
        env.process(paciente(env, f"Paciente-{i}", enfermeras, doctores, rayosx, laboratorio))


def correr_simulacion():
    random.seed(RANDOM_SEED)
    env = simpy.Environment()

    enfermeras = simpy.Resource(env, NUM_ENFERMERAS)
    doctores = simpy.PriorityResource(env, NUM_DOCTORES)
    rayosx = simpy.PriorityResource(env, NUM_RAYOSX)
    laboratorio = simpy.PriorityResource(env, NUM_LAB)

    env.process(generador_pacientes(env, enfermeras, doctores, rayosx, laboratorio))
    env.run(until=TIEMPO_SIMULACION)

    # Mostrar resultados
    plt.hist(tiempos_espera, bins=20, color='skyblue', edgecolor='black')
    plt.title("Tiempos totales de atención por paciente")
    plt.xlabel("Tiempo total en sistema")
    plt.ylabel("Cantidad de pacientes")
    plt.grid(True)
    plt.show()


if __name__ == "__main__":
    correr_simulacion()
