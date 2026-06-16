# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Raw lab assets (Kubernetes manifests, configs, and application source) for the [cp-flink-workshop](https://github.com/confluentinc/cp-flink-workshop) labs, targeting **CMF 2.3 / CFK 3.2.x**. Lab instructions, slide decks, themes, and build tooling stay in `cp-flink-workshop` — this repo holds only what attendees apply, build, or deploy. Never add instruction markdown (Marp decks) here.

Canonical home: `github.com/confluentinc/cp-flink-workshop-labs`.

## Commands

```bash
# Build and test the Lab 05 Flink job (requires Java 21)
mvn -f lab-05/resources/ci-app/pom.xml verify

# Run only its unit tests
mvn -f lab-05/resources/ci-app/pom.xml test

# Validate a manifest after editing
kubectl apply --dry-run=client -f <file>   # not lab-03/resources/compute-pool.json — that's a CMF REST payload, not a manifest
```

## Repository Structure

Assets are grouped by the lab that uses them: `lab-NN/resources/`. Numbering gaps are intentional (labs without standalone assets, or not yet authored). Assets must not reference another lab's directory — duplicate a file into the lab that needs it instead.

The contract with `cp-flink-workshop` is that its lab instructions reference these files by repo-root-relative path (e.g., `lab-02/resources/postgres.yaml`), so renaming or moving a file here breaks the workshop docs.

## Gotchas

- **Two different clusters.** lab-02/lab-03 assets target the hand-built cluster (FlinkEnvironment `flink-env`, CMFRestClass in namespace `operator`); lab-05 targets the Lab 1 GitOps cluster (FlinkEnvironment `env1`, CMFRestClass in namespace `flink`). The differing names/namespaces are correct, not a bug.
- **Lab 05's Tekton pipeline clones this repo's `main` branch** (`lab-05/resources/tekton/pipelinerun.yaml` + `task-fetch-source.yaml`). Lab-05 asset changes only take effect for attendees once merged to `main`; renaming the repo breaks the lab.
- **Cutover pending:** the workshop decks still reference their local `labs/resources/` copies. Until those are repointed at this repo and deleted, the two repos hold duplicate assets that must be kept in sync manually.
- Never commit `lab-05/resources/ci-app/target/` build output (gitignored at root and in `ci-app/.gitignore`).
- Secrets in manifests are placeholder values that attendees substitute — keep it that way.

## Branching & Workflow

- Never commit directly to `main`; create PRs for all changes and never force push to `main`.
- Branch names follow `feature/<description>` or `fix/<description>`.
- Worktree isolation is recommended for parallel feature work: `claude --worktree` (handles worktree/branch creation and cleanup), or manually `git worktree add -b feature/<name> ../cp-flink-workshop-labs-<name>`.
- Keep commits focused on single changes; update the root `README.md` when lab directories are added, renamed, or renumbered.
