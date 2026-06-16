# cp-flink-workshop-labs

This repository holds the public lab assets for the Confluent Platform for Apache Flink Workshop, available internally at [confluentinc/cp-flink-workshop](https://github.com/confluentinc/cp-flink-workshop).

## Overview

Raw lab assets (Kubernetes manifests, configs, and application source) for the hands-on labs in the [cp-flink-workshop](https://github.com/confluentinc/cp-flink-workshop) repository — a workshop on deploying and operating **Confluent Platform for Apache Flink** on Kubernetes.

Lab instructions, slide decks, themes, and build tooling live in `cp-flink-workshop`. This repository holds only the files attendees apply, build, or deploy during the labs.

## Platform Versions

Currently targeted for **CMF 2.3** / **CFK 3.2.x**.

## Repository Layout

Assets are grouped by the lab that uses them, following the workshop's lab numbering:

| Directory | Used by | Contents |
|-----------|---------|----------|
| `lab-02/resources/` | Lab 02 — Install CP Flink by Hand | kind cluster config, Postgres, CFK/CMF platform manifests |
| `lab-03/resources/` | Lab 03 — Deploy a FlinkApplication and Flink Statement | FlinkApplication manifest, compute pool definition |
| `lab-05/resources/` | Lab 05 — Build an End-to-End CI Pipeline | `ci-app/` Maven Flink job, Avro schema, Tekton pipeline and tasks |

Gaps in numbering are intentional: Lab 01 has no standalone assets (it bootstraps from a GitOps repository), and Lab 04 is not yet authored.

The contract with the workshop is that lab instructions reference these files with paths relative to this repository's root (e.g., `kubectl apply -f lab-02/resources/postgres.yaml`).

## Contributions

This repository is part of the Confluent organization on GitHub. It is public and open to contributions from the community.

## License

Please see the LICENSE file for contribution terms.

## Updates and Changes

Please see the CHANGELOG.md for details of recent updates.