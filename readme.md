Broomstick
==========

![PaperMC 1.20.4](https://img.shields.io/badge/PaperMC-1.20.4-black.svg?style=flat-square&logoColor=white&labelColor=62B47A)
![Resource Pack Version 22](https://img.shields.io/badge/ResourcePack-v22-black.svg?style=flat-square&logoColor=white&labelColor=62B47A)

シンプルな魔法のほうきを追加するプラグインです。

## Getting Started
### 導入方法 (サーバーサイド)
このリポジトリをクローンし、プラグインをビルドするか、リリースページからjarファイルをダウンロードします。
その後、プラグインのjarファイルをPaperMCサーバーのpluginsディレクトリに配置します。
### 導入方法 (クライアントサイド)
このリポジトリの`Broomstick-resourcepack.zip`をダウンロードし、リソースパックを読み込みます。

### アイテムの入手
以下のコマンドを実行し、「魔法のほうき」を入手します
```
/give @p stick{display:{Name:'{"text":"魔法のほうき","color":"yellow"}'},CustomModelData:1}
```
### 使い方
乗り物に乗っている場合は降りて、落下していない状態で「魔法のほうき」をメインハンドもしくはオフハンドに持って右クリックするとほうきに乗ることができます。
降りる操作はトロッコなどと同様です。
プレイヤーが降りてからしばらくすると、ほうきが手元に戻ってきます。