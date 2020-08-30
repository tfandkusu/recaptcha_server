# recaptcha_server

Qiita記事「[AndroidのreCAPTCHA APIを使う【私はロボットではありません】](https://qiita.com/tfandkusu/items/8d1b1031f555ec77236f)」を書くために作成したサーバサイドアプリケーションです。

## 各アプリ固有情報の設定

- デプロイするGCPのプロジェクトIDをPROJECT_ID環境変数に設定します。
- [template-app-engine-web.xml](https://github.com/tfandkusu/recaptcha_server/blob/master/src/main/webapp/WEB-INF/template-app-engine-web.xml)ファイルをapp-engine-web.xmlにリネームして、各アプリ固有の情報を入力します。
