# cp-flink-workshop-labs

This repository is part of the Confluent organization on GitHub.
It is public and open to contributions from the community.

Please see the LICENSE file for contribution terms.
Please see the CHANGELOG.md for details of recent updates.

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

> **Cutover status:** the `cp-flink-workshop` decks have not yet been updated — they still reference their local `labs/resources/` copies. Until that cutover lands (deck paths updated, old copies deleted), this repository and the workshop's `labs/resources/` are duplicate sources that must be kept in sync manually.
