compilar:
	./mvnw clean package -DskipTests

compilarN:
	./mvnw clean package -DskipTests -Dnative

dockerBuild:
	docker build -f infra/docker/duck.dockerfile -t rasp.local:5000/duckapi-jvm .

dockerPush:
	docker push rasp.local:5000/duckapi-jvm

dockerup:
	docker compose -f infra/docker/docker-compose.yml up -d

dockerupb:
	docker compose -f infra/docker/docker-compose.yml up -d --build

dockerdown:
	docker compose -f infra/docker/docker-compose.yml down

k8sApply:
	kubectl apply -f infra/k8s/duck-all.yml

k8sDelete:
	kubectl delete -f infra/k8s/duck-all.yml