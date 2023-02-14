.PHONY: infra-plan
infra-plan:
	terraform plan

.PHONY: infra-apply
infra-apply:
	terraform apply

.PHONY: format-code
format-code:
	./gradlew ktlintFormat
